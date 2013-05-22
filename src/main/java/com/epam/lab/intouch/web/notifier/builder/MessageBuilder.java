package com.epam.lab.intouch.web.notifier.builder;

import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.project.Project;

public class MessageBuilder {
	private StringBuilder messageText;
	private StringBuilder messageSubject;
	private Project project;
	private Member member;

	private static final String projectURL = "http://localhost:8080/InTouch/project?id=";

	public MessageBuilder(Project project, Member member) {
		this.project = project;
		this.member = member;
		messageText = new StringBuilder();
		messageSubject = new StringBuilder();
	}

	public String builText() {
		if (member == null) {
			messageText.append("There have been changes in ").append(project.getProjectName()).append(" project.\n");
			messageText.append(" Some information about it: \n");
			messageText.append(" - project name: ").append(project.getProjectName()).append("\n");
			messageText.append(" - status: ").append(project.getStatus()).append("\n");
			messageText.append(" - customer: ").append(project.getCustomer()).append("\n");
			messageText.append(" - creation date: ").append(project.getCreationDate()).append("\n");
			messageText.append(" - estimated completion date: ").append(project.getEstimatedCompletionDate()).append("\n");

			messageText.append(" For more information visit our website for your reference: ").append(projectURL).append(project.getId());
		} else {
			messageText.append("There have been changes in ").append(project.getProjectName()).append(" project.\n");
			messageText.append(" For more information visit our website for your reference: ").append(projectURL).append(project.getId());
		}

		return messageText.toString();
	}

	public String buildSubject() {

		messageSubject.append("[inTouch] Changes in ").append(project.getProjectName()).append(" project");

		return messageSubject.toString();
	}

}
