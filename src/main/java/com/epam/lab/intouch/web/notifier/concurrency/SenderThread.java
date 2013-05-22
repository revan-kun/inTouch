package com.epam.lab.intouch.web.notifier.concurrency;

import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.project.Project;
import com.epam.lab.intouch.web.notifier.email.MailSender;

public class SenderThread implements Runnable {

	private MailSender mailSender;
	private static Project project;
	private static Member member;

	public SenderThread(Project project, Member member) {
		this.project = project;
		this.member = member;
		mailSender = new MailSender(project, member);
	}

	@Override
	public void run() {

		mailSender.sendMessage(project, member);

	}

}
