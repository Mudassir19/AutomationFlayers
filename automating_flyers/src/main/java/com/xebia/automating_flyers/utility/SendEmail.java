package com.xebia.automating_flyers.utility;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendEmail {

	private static final String CLASS_NAME = SendEmail.class.getName();

	private static final Logger LOGGER = LoggerFactory.getLogger(CLASS_NAME);

	public static String sendEmail(String subject, String imagePath) {

		String port = "587";
		/*
		 * String host = "smtp.gmail.com"; String userName = "smudassir70@gmail.com";
		 * String password = "Muda@1234";
		 */
		
		 // String host = "smtp.office365.com"; 
		/*
		 * String userName ="HRteamXebiaIndia@xebia.com"; String password
		 * ="HpTdGp4JWubWZ0iiyELq40D3WkZmz06fu/wkRNBAgBQ=";
		 */
		 

		 String host = "outlook.office365.com";

		// String host ="smtp.office365.com"; 
		 String userName="mudassir.shaikh@xebia.com"; 
		 String password = "Muda@123";
		

		// String subject="Test Mail_OTP";

		// String toAddress = "mudassir.shaikh@xebia.com,mudassir.shaikh@xebia.com";
		// String[] toAddress = new String[2];

		String status;

		// sets SMTP server properties
		Properties properties = new Properties();

		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.ssl.enable", "true");//new
		properties.put("mail.smtp.user", userName);

		/*
		 * properties.put("mail.smtp.auth", "true");
		 * properties.put("mail.smtp.starttls.enable", "true");
		 * properties.put("mail.smtp.host", "smtp.office365.com");
		 * properties.put("mail.smtp.port", "587");
		 * properties.put("mail.smtp.ssl.enable", "true");
		 * properties.put("mail.debug.auth", "true");
		 */

		// creates a new session, no Authenticator (will connect() later)
		Session session = Session.getDefaultInstance(properties);

		// creates a new e-mail message
		try {
			Message msg = new MimeMessage(session);

			msg.setFrom(new InternetAddress(userName));

			String toAdd = "mudassir.shaikh@xebia.com";
			String[] recipientList = toAdd.split(",");
			InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
			int counter = 0;
			for (String recipient : recipientList) {
				recipientAddress[counter] = new InternetAddress(recipient.trim());
				counter++;
			}
			msg.setRecipients(Message.RecipientType.TO, recipientAddress);

			// InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
			// msg.setRecipients(Message.RecipientType.TO, toAddresses);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			// set plain text message
			// msg.setText(message);

			// This mail has 2 part, the BODY and the embedded image
			MimeMultipart multipart = new MimeMultipart("related");

			// first part (the html)
			BodyPart messageBodyPart = new MimeBodyPart();
			String htmlText = "<center><img src=\"cid:image\"></center>";
			messageBodyPart.setContent(htmlText, "text/html");
			// add it
			multipart.addBodyPart(messageBodyPart);

			// second part (the image)
			messageBodyPart = new MimeBodyPart();
			DataSource fds = new FileDataSource(imagePath);

			messageBodyPart.setDataHandler(new DataHandler(fds));
			messageBodyPart.setHeader("Content-ID", "<image>");

			// add image to the multipart
			multipart.addBodyPart(messageBodyPart);

			// put everything together
			msg.setContent(multipart);

			Transport t = session.getTransport("smtp");
			t.connect(userName, password);
			t.sendMessage(msg, msg.getAllRecipients());
			t.close();
			status = "success";

		} catch (Exception e) {

			status = "failed";
			e.printStackTrace();

		}
		return status;
	}

}
