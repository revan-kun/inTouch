package pseudotests;

import com.epam.lab.intouch.dao.exception.DAOReadException;
import com.epam.lab.intouch.dao.member.DefaultMemberDAO;
import com.epam.lab.intouch.model.member.Member;

public class MemberDAOTest {

	/**
	 * @param args
	 * @throws DAOReadException 
	 */
	public static void main(String[] args)  {

		DefaultMemberDAO memberDAO = new DefaultMemberDAO();
		Member member;
		try {
			member = memberDAO.getById("dobkin@epam.com");
			System.out.println(member.getFirstName() + member.getLastName() + member.getLogin());
		} catch (DAOReadException e) {
			System.out.println(e.getMessage());
		}
		

	}

}
