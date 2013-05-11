package pseudotests;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.service.member.MemberService;

public class MemberServiceTEST {

	/**
	 * @param args
	 * @throws DAOException 
	 */
	public static void main(String[] args) throws DAOException {
		
		MemberService memberService = new MemberService();
		Member member = memberService.getById("dobkin@epam.com");
		memberService.memberWithFullActiveProject(member.getLogin());

	}

}
