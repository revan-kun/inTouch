package com.epam.lab.intouch.web.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * EmailPropertie class has propertie for email sending
 * 
 * @author Iryna
 *
 */
public class EmailPropertie {

	/**
	 * Method to configured all necessary properties for mail sending
	 * 
	 * @param host
	 * @param port
	 * @param userName
	 * @param password
	 * @param toAddress
	 * @param message
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public static void sendEmail(String host, String port, final String userName, final String password, String toAddress, String message)
			throws AddressException, MessagingException {

		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.host", host);

		properties.put("mail.smtp.port", port);


		properties.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		});

		// creates a new e-mail message
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(userName));
		InternetAddress[] toAddresses = { new InternetAddress(toAddress) };

		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSentDate(new Date());
		msg.setText(message);

		// sends the e-mail

		Transport transport = session.getTransport("smtp");

		transport.connect(host, userName, password);
		transport.sendMessage(msg, msg.getAllRecipients());
		transport.close();

	}

}
