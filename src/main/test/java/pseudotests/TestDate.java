package pseudotests;

import com.epam.lab.intouch.dao.exception.DAOReadException;
import com.epam.lab.intouch.dao.member.like.DefaultLikeDAO;
import com.epam.lab.intouch.model.member.Member;

public class TestDate {

	/**
	 * @param args
	 * @throws DAOReadException
	 */
	public static void main(String[] args) throws DAOReadException {
		// System.out.println(new Time(new Date().getTime()));
		DefaultLikeDAO dao = new DefaultLikeDAO();
		Member owner = new Member();
		owner.setLogin("smith@epam.com");
		Member liker = new Member();
		liker.setLogin("franko77@ukr.net");
		System.out.println(dao.getStatus(owner, liker).toString());

	}

}
