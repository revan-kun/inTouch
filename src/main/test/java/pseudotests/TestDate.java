package pseudotests;

import com.epam.lab.intouch.dao.exception.DAOCreateException;
import com.epam.lab.intouch.dao.exception.DAOReadException;
import com.epam.lab.intouch.dao.member.DefaultMemberDAO;
import com.epam.lab.intouch.dao.member.like.DefaultLikeDAO;
import com.epam.lab.intouch.model.member.Member;

public class TestDate {

	/**
	 * @param args
	 * @throws DAOReadException
	 * @throws DAOCreateException 
	 */
	public static void main(String[] args) throws DAOReadException, DAOCreateException {
		// System.out.println(new Time(new Date().getTime()));
		DefaultMemberDAO dao = new DefaultMemberDAO();
		Member owner = new Member();
		owner.setLogin("koccckok@epam.com");
		owner.setPassword("серий123456");
		owner.setFirstName("Сергій");
		owner.setLastName("Конончук");
		owner.setAdditionalInfo("я бігав-бігав і бігав");
		
		
		dao.create(owner);
	/*	Member liker = new Member();
		liker.setLogin("franko77@ukr.net");
		System.out.println(dao.getStatus(owner, liker).toString());*/

	}

}
