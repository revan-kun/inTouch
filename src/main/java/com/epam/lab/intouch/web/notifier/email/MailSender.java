package com.epam.lab.intouch.web.notifier.email;

import static com.epam.lab.intouch.web.notifier.util.PropertiesReader.getProperty;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.project.Project;
import com.epam.lab.intouch.web.notifier.builder.MessageBuilder;

public class MailSender {

	private final static Logger LOG = LogManager.getLogger(MailSender.class);
	private static final String PATH = "mail_sender.properties";
	private static final String from = getProperty(PATH, "from");
	private static final String password = getProperty(PATH, "password");
	private static final String host = getProperty(PATH, "host");
	private static final String mailSMTPPort = getProperty(PATH, "mailSMTPPort");
	private static MessageBuilder mailBuilder;

	public MailSender(Project project) {

		mailBuilder = new MessageBuilder(project);

	}

	public void sendMessage(Project project) {

		send(getResipients(project));
		LOG.info("Messages was send to resipients");

	}

	private static void send(List<String> resipients) {

		// Get system properties
		Properties properties = System.getProperties();
		// Setup mail server
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.user", from);
		properties.put("mail.smtp.password", password);
		properties.put("mail.smtp.port", mailSMTPPort);
		properties.put("mail.smtp.auth", "true");

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));
			for (int i = 0; i < resipients.size(); i++) {
				// Set To: header field of the header.
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(resipients.get(i)));

			}
			// Set Subject: header field
			message.setSubject(mailBuilder.buildSubject());

			// Now set the actual message
			message.setText(mailBuilder.builText());

			// Send message
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

		} catch (MessagingException e) {
			LOG.error("Problem with sending message to email ", e);
		}
	}

	private List<String> getResipients(Project project) {

		List<String> emails = new LinkedList<String>();
		List<Member> members = project.getMembers();
		for (Member member : members) {
			String email = member.getLogin();
			emails.add(email);
		}
		emails.add("molodec@email.ua");

		return emails;
	}
}
