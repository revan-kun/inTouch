package com.epam.lab.intouch.service.member;

import static com.epam.lab.intouch.util.db.metadata.PhotoName.DEVELOPER_PHOTO;
import static com.epam.lab.intouch.util.db.metadata.PhotoName.MANAGER_PHOTO;
import static com.epam.lab.intouch.util.db.metadata.PhotoName.TESTER_PHOTO;

import java.util.LinkedList;
import java.util.List;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.dao.factory.AbstractDAOFactory;
import com.epam.lab.intouch.dao.history.project.HistoryDAO;
import com.epam.lab.intouch.dao.member.MemberDAO;
import com.epam.lab.intouch.dao.member.skill.MemberSkillsDAO;
import com.epam.lab.intouch.dao.member.skill.SkillDAO;
import com.epam.lab.intouch.dao.project.ProjectDAO;
import com.epam.lab.intouch.dao.team.TeamDAO;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.info.skill.Skill;
import com.epam.lab.intouch.model.project.Project;

/**
 * MemberService class combine different method from DAO class to get object
 * which we need
 * 
 * @author Iryna
 * 
 */

public class MemberService implements BaseMemberService {

	private final MemberDAO memberDAO;
	private final ProjectDAO projectDAO;
	private final TeamDAO teamDAO;
	private final HistoryDAO historyDAO;
	private final SkillDAO skillDAO;
	private final MemberSkillsDAO memberSkillsDAO;

	/**
	 * Initialization required DAO classes for Member Service
	 */
	public MemberService() {
		AbstractDAOFactory factory = AbstractDAOFactory
				.getInstance(AbstractDAOFactory.MS_SERVER);
		memberDAO = factory.getMemberDAO();
		projectDAO = factory.getProjectDAO();
		teamDAO = factory.getTeamDAO();
		historyDAO = factory.getHistoryDAO();
		skillDAO = factory.getSkillDAO();
		memberSkillsDAO = factory.getMemberSkillsDAO();
	}

	/**
	 * Method for creating member with his skills
	 * 
	 * @see com.epam.lab.intouch.service.member.BaseMemberService#create(com.epam.lab.intouch.model.member.Member)
	 * @param member
	 * @throws DAOException
	 * @return memberLogin
	 */
	@Override
	public String create(Member member) throws DAOException {

		String memberLogin = memberDAO.create(member);
		memberSkillsDAO.create(member);

		return memberLogin;
	}

	/**
	 * Method for getting by id member with his skills and set default photo by
	 * his role in the project
	 * 
	 * @see com.epam.lab.intouch.service.member.BaseMemberService#getById(java.lang.String)
	 * @param login
	 * @throws DAOException
	 * @return fullMember
	 */
	@Override
	public Member getById(String login) throws DAOException {

		Member fullMember = memberDAO.getById(login);

		if (fullMember != null) {
			List<Skill> additionalSkills = memberSkillsDAO.getById(login)
					.getSkills();
			for (Skill additionalSkill : additionalSkills) {
				Skill almostFullSkill = skillDAO.getById(additionalSkill
						.getId());

				additionalSkill.setName(almostFullSkill.getName());
				additionalSkill.setSkillType(almostFullSkill.getSkillType());
			}

			fullMember.setSkills(additionalSkills);
			if (fullMember.getPhotoLink() == null
					|| fullMember.getPhotoLink().isEmpty()) {
				if (fullMember.isManager()) {
					fullMember.setPhotoLink(MANAGER_PHOTO);
				} else if (fullMember.isDeveloper()) {
					fullMember.setPhotoLink(DEVELOPER_PHOTO);
				} else {
					fullMember.setPhotoLink(TESTER_PHOTO);
				}
			}

		}

		return fullMember;
	}

	/**
	 * Method for updating member info
	 * 
	 * @see com.epam.lab.intouch.service.member.BaseMemberService#update(com.epam.lab.intouch.model.member.Member,
	 *      com.epam.lab.intouch.model.member.Member)
	 * @param oldMember
	 *            (member with old parametres)
	 * @param newMember
	 *            (member with new parametres)
	 * @throws DAOException
	 */
	@Override
	public void update(Member oldMember, Member newMember) throws DAOException {

		memberDAO.update(oldMember, newMember);
	}

	/**
	 * Method for deleting member
	 * 
	 * @see com.epam.lab.intouch.service.member.BaseMemberService#delete(com.epam.lab.intouch.model.member.Member)
	 * @param member
	 * @throws DAOException
	 */
	@Override
	public void delete(Member member) throws DAOException {

		memberDAO.delete(member);
	}

	/**
	 * Method for getting member with his projects
	 * 
	 * @param members
	 * @return fullMembers
	 * @throws DAOException
	 */
	private List<Member> getFullMembers(List<Member> members)
			throws DAOException {

		List<Member> fullMembers = new LinkedList<Member>();
		List<Project> fullProjects = new LinkedList<Project>();

		for (Member member : members) {
			String login = member.getLogin();
			Member fullMember = getById(login);

			Member memberWithHistoryProjectIds = historyDAO.getById(login);
			List<Project> historyProjects = memberWithHistoryProjectIds
					.getHistoryProjects();

			for (Project project : historyProjects) {
				fullProjects.add(projectDAO.getById(project.getId()));
			}
			fullMember.setHistoryProjects(historyProjects);

			List<Project> fullActiveProjects = new LinkedList<Project>();
			Member memberWithActiveProject = teamDAO.getActiveProjects(login);
			List<Project> activeProjects = memberWithActiveProject
					.getActiveProjects();
			for (Project project : activeProjects) {

				fullActiveProjects.add(project);

			}
			fullMember.setActiveProjects(fullActiveProjects);

			fullMembers.add(fullMember);
		}

		return fullMembers;

	}

	/**
	 * Method for getting member with his personal info and active projects only
	 * with id
	 * 
	 * @see com.epam.lab.intouch.service.member.BaseMemberService#memberWithActiveProjectId(java.lang.String)
	 * @param login
	 * @return member
	 * @throws DAOException
	 */
	@Override
	public Member memberWithActiveProjectId(String login) throws DAOException {

		Member member = memberDAO.getById(login);
		Member memberWithActiveProjectId = teamDAO.getActiveProjects(login);
		List<Project> activeProjectsId = memberWithActiveProjectId
				.getActiveProjects();
		List<Project> activeProjects = new LinkedList<Project>();
		for (Project project : activeProjectsId) {
			activeProjects.add(project);
		}
		member.setActiveProjects(activeProjects);

		return member;
	}

	/**
	 * Method for getting member with his personal info and active projects info
	 * 
	 * @see com.epam.lab.intouch.service.member.BaseMemberService#memberWithFullActiveProject(java.lang.String)
	 * @param login
	 * @return member
	 * @throws DAOException
	 */
	@Override
	public Member memberWithFullActiveProject(String login) throws DAOException {
		Member member = memberDAO.getById(login);

		Member memberWithActiveProjectId = teamDAO.getActiveProjects(login);

		List<Project> activeProjectsId = memberWithActiveProjectId
				.getActiveProjects();

		List<Project> activeProjects = new LinkedList<Project>();

		for (Project project : activeProjectsId) {

			Project fullProject = projectDAO.getById(project.getId());

			project.setProjectName(fullProject.getProjectName());
			project.setCreationDate(fullProject.getCreationDate());
			project.setEstimatedCompletionDate(fullProject.getCompletionDate());
			project.setCompletionDate(fullProject.getCompletionDate());
			project.setDescription(fullProject.getDescription());
			project.setCustomer(fullProject.getCustomer());
			project.setStatus(fullProject.getStatus());

			Project projectWithMemberId = teamDAO.getById(project.getId());
			List<Member> membersId = projectWithMemberId.getMembers();
			List<Member> members = new LinkedList<Member>();

			for (Member memberId : membersId) {

				Member memberFromProject = memberDAO.getById(memberId
						.getLogin());

				memberId.setAdditionalInfo(memberFromProject
						.getAdditionalInfo());
				memberId.setBirthday(memberFromProject.getBirthday());
				memberId.setExperience(memberFromProject.getExperience());
				memberId.setFirstName(memberFromProject.getFirstName());
				memberId.setLastName(memberFromProject.getLastName());
				memberId.setPassword(memberFromProject.getPassword());
				memberId.setPhotoLink(memberFromProject.getPhotoLink());
				memberId.setQualificationLevel(memberFromProject
						.getQualificationLevel());
				memberId.setRating(memberFromProject.getRating());
				memberId.setRegistrationDate(memberFromProject
						.getRegistrationDate());
				memberId.setRole(memberFromProject.getRole());
				memberId.setSex(memberFromProject.getSex());

				members.add(memberId);
			}

			project.setMembers(members);
			activeProjects.add(project);
		}
		member.setActiveProjects(activeProjects);

		return member;
	}

	/**
	 * Method for getting members with his personal information and projects
	 * information
	 * 
	 * @see com.epam.lab.intouch.service.member.BaseMemberService#getAll()
	 * @return fullMembers
	 * @throws DAOException
	 */
	@Override
	public List<Member> getAll() throws DAOException {

		List<Member> members = (List<Member>) memberDAO.getAll();
		List<Member> fullMembers = getFullMembers(members);

		return fullMembers;
	}

	/**
	 * Method for getting all members from search
	 * 
	 * @see com.epam.lab.intouch.service.member.BaseMemberService#getAllFromSearch(java.lang.String)
	 * @param query
	 * @return fullMembers
	 * @throws DAOException
	 */
	@Override
	public List<Member> getAllFromSearch(String query) throws DAOException {

		List<Member> members = (List<Member>) memberDAO.getAllFromSearch(query);
		List<Member> fullMembers = getFullMembers(members);

		return fullMembers;
	}

	/**
	 * Method update rating of member
	 * 
	 * @see com.epam.lab.intouch.service.member.BaseMemberService#updateRating(com.epam.lab.intouch.model.member.Member)
	 * @param member
	 * @throws DAOException
	 * 
	 */
	@Override
	public void updateRating(Member member) throws DAOException {

		memberDAO.updateRating(member);

	}

	/**
	 * Method for getting projects from history by member login
	 * 
	 * @see com.epam.lab.intouch.service.member.BaseMemberService#getMemberProjectsHistory(java.lang.String)
	 * @param login
	 * @return fullProjects
	 * @throws DAOException
	 */
	@Override
	public List<Project> getMemberProjectsHistory(String login)
			throws DAOException {

		List<Project> fullProjects = new LinkedList<Project>();

		Member memberWithActiveProject = teamDAO.getActiveProjects(login);
		List<Project> activeProjects = memberWithActiveProject
				.getActiveProjects();
		for (Project project : activeProjects) {

			fullProjects.add(projectDAO.getById(project.getId()));
		}

		Member memberWithHistoryProject = historyDAO.getById(login);
		List<Project> historyProjects = memberWithHistoryProject
				.getHistoryProjects();
		for (Project project : historyProjects) {

			fullProjects.add(projectDAO.getById(project.getId()));
		}

		return fullProjects;
	}

	/**
	 * Method for getting member with his active projects
	 * 
	 * @see com.epam.lab.intouch.service.member.BaseMemberService#memberWithActiveProjectInfo(java.lang.String)
	 * @param login
	 * @return member
	 * @throws DAOException
	 */
	@Override
	public Member memberWithActiveProjectInfo(String login) throws DAOException {

		Member member = memberDAO.getById(login);
		Member memberWithActiveProjectId = teamDAO.getActiveProjects(login);
		List<Project> activeProjectsId = memberWithActiveProjectId
				.getActiveProjects();
		List<Project> activeProjects = new LinkedList<Project>();
		for (Project project : activeProjectsId) {

			Project projectInfo = projectDAO.getById(project.getId());

			project.setCompletionDate(projectInfo.getCompletionDate());
			project.setCreationDate(projectInfo.getCreationDate());
			project.setCustomer(projectInfo.getCustomer());
			project.setDescription(projectInfo.getDescription());
			project.setEstimatedCompletionDate(projectInfo
					.getEstimatedCompletionDate());
			project.setProjectName(projectInfo.getProjectName());
			project.setStatus(projectInfo.getStatus());

			activeProjects.add(project);
		}
		member.setActiveProjects(activeProjects);

		return member;
	}

}
