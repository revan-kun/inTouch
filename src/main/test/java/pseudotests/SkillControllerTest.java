package pseudotests;

import com.epam.lab.intouch.controller.exception.DataAccessingException;
import com.epam.lab.intouch.controller.skill.SkillController;
import com.epam.lab.intouch.model.member.info.skill.SkillType;

public class SkillControllerTest {

	/**
	 * @param args
	 * @throws DataAccessingException 
	 */
	public static void main(String[] args) throws DataAccessingException {
		SkillController controller=new SkillController();
		System.out.println(controller.getSkills(SkillType.LANGUAGE)+"\n");
		System.out.println(controller.getSkills(SkillType.PROGRAMMING)+"\n");
		System.out.println(controller.getSkills(SkillType.TECHNOLOGY)+"\n");

		System.out.println(controller.getSkillTypes());
	}

}
