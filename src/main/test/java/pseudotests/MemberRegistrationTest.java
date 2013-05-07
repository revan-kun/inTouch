package pseudotests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.intouch.controller.exception.DataAccessingException;
import com.epam.lab.intouch.controller.member.common.MemberController;
import com.epam.lab.intouch.dao.util.ConnectionManager;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.enums.Role;
import com.epam.lab.intouch.model.member.enums.Sex;

public class MemberRegistrationTest {
	private final static Logger LOG = LogManager.getLogger(ConnectionManager.class);

	public static void main(String[] args) {

		Member member = new Member();
		member.setFirstName("John");
		member.setLastName("Teylor");
		member.setLogin("testmanager2@gmail.com");
		member.setPassword("1111");
		member.setRole(Role.DEVELOPER);
		member.setSex(Sex.MALE);

		MemberController memberController = new MemberController();
		Boolean wasRegistered = null;
		try {
			wasRegistered = memberController.registerNewMember(member);
			LOG.info(" Was registered: " + wasRegistered);
		} catch (DataAccessingException e) {
			LOG.error(" DB trouble: ", e);
		}

	}

}
