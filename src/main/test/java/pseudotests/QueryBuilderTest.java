package pseudotests;

import com.epam.lab.intouch.controller.util.query.builder.SelectQuery;
import com.epam.lab.intouch.controller.util.query.from.Table;
import com.epam.lab.intouch.controller.util.query.join.InnerJoin;
import com.epam.lab.intouch.controller.util.query.join.JoinCondition;
import com.epam.lab.intouch.controller.util.query.join.JoinOperand;
import com.epam.lab.intouch.controller.util.query.select.Column;
import com.epam.lab.intouch.controller.util.query.select.SelectOperand;
import com.epam.lab.intouch.controller.util.query.select.WildCard;
import com.epam.lab.intouch.controller.util.query.where.Condition;
import com.epam.lab.intouch.controller.util.query.where.ConditionGroup;
import com.epam.lab.intouch.controller.util.query.where.GroupOperator;
import com.epam.lab.intouch.controller.util.query.where.Operator;
import com.epam.lab.intouch.util.db.metadata.FieldName;
import com.epam.lab.intouch.util.db.metadata.TableName;

public class QueryBuilderTest {
	public static void main(String[] args) {

		final String INPUT_SKILL_NAME = "ENGLISH";
		final String INPUT_SKILL_EXPERIENCE = "2";

		Table members = new Table(TableName.MEMBER);
		Table memberSkills = new Table(TableName.MEMBER_SKILLS);
		Table skills = new Table(TableName.SKILLS);

		Column memberID = new Column(FieldName.MEMBER_ID);
		memberID.setTable(memberSkills);

		Column memberLogin = new Column(FieldName.LOGIN);
		memberLogin.setTable(members);

		Column id = new Column(FieldName.ID);
		id.setTable(skills);

		Column skillID = new Column(FieldName.SKILL_ID);
		skillID.setTable(memberSkills);

		Column skillExperience = new Column(FieldName.EXPERIENCE);
		skillExperience.setTable(memberSkills);

		Column skillName = new Column(FieldName.NAME);
		skillName.setTable(skills);

		SelectOperand selectOperand = new WildCard();

		JoinCondition memberSkillsMemberCond = new JoinCondition(memberID, Operator.EQUALS, memberLogin);
		JoinOperand memberSkillsMemberJoin = new InnerJoin(members, memberSkills, memberSkillsMemberCond);

		JoinCondition skillsMemberSkillsCond = new JoinCondition(id, Operator.EQUALS, skillID);
		JoinOperand skillsMemberSkillJoin = new InnerJoin(skills, memberSkills, skillsMemberSkillsCond);

		Condition skillNameCond = new Condition(skillName, Operator.EQUALS, INPUT_SKILL_NAME);
		Condition skillExpCond = new Condition(skillExperience, Operator.EQUALS, INPUT_SKILL_EXPERIENCE);

		ConditionGroup condGroup = new ConditionGroup(GroupOperator.AND);
		condGroup.addWhereOperand(skillNameCond);
		condGroup.addWhereOperand(skillExpCond);

		SelectQuery query = new SelectQuery();

		query.setConditionGroup(condGroup);
		query.addSelectOperand(selectOperand);
		query.addJoinOperand(memberSkillsMemberJoin);
		query.addJoinOperand(skillsMemberSkillJoin);

		System.out.println(query.write());

	}
}
