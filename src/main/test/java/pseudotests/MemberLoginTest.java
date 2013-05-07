package pseudotests;

import com.epam.lab.intouch.controller.exception.DataAccessingException;
import com.epam.lab.intouch.controller.member.common.MemberController;
import com.epam.lab.intouch.model.member.Member;

public class MemberLoginTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MemberController memberController = new MemberController();
		try {
			Member member = memberController.authorizeMember("carlos@gmail.com", "2222");
			System.out.println(member);
		} catch (DataAccessingException e) {
			System.out.println(" DB problem: " + e);
		}

	}

}
