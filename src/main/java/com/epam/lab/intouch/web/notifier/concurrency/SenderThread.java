package com.epam.lab.intouch.web.notifier.concurrency;

import com.epam.lab.intouch.model.project.Project;
import com.epam.lab.intouch.web.notifier.email.MailSender;

public class SenderThread implements Runnable {

	private MailSender mailSender;
	private static Project project;

	public SenderThread(Project project) {
		this.project = project;
		mailSender = new MailSender(project);
	}

	@Override
	public void run() {

		mailSender.sendMessage(project);

	}

}
