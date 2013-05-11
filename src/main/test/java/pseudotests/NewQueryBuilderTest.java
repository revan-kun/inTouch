package pseudotests;

import com.epam.lab.intouch.controller.util.query.builder.QueryBuilder;
import com.epam.lab.intouch.controller.util.query.from.Table;
import com.epam.lab.intouch.controller.util.query.select.Wildcard;
import com.epam.lab.intouch.controller.util.query.where.Condition;
import com.epam.lab.intouch.controller.util.query.where.Operator;
import com.epam.lab.intouch.model.member.enums.QualificationLevel;
import com.epam.lab.intouch.util.db.metadata.TableName;

public class NewQueryBuilderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		QueryBuilder qb = new QueryBuilder();

		Table member = new Table(TableName.MEMBER);
		Table memberSkills = new Table(TableName.MEMBER_SKILLS);

		Condition memberSkillsMemberKey = new Condition(member.getColumn("login"), Operator.EQUALS, memberSkills.getColumn("member_id"));

		Condition experience = new Condition(member.getColumn("experience"), Operator.GREATER, "2", true);
		Condition qlevel = new Condition(member.getColumn("qlevel"), Operator.EQUALS, QualificationLevel.MIDDLE.name(), true);

		qb.select(new Wildcard())
		.from(member)
		.inerJoin(memberSkills, memberSkillsMemberKey)
		.where(experience)
		.and(qlevel);

		System.out.println(qb);
	}

}
