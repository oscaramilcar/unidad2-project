package com.unit2.project;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailSend {
	public static void emailSendAttachment(String pathFilePDF,String pathFileTxt, String email) {
		
		final String username = "pc340355@gmail.com";
		final String password = "P@ss.$$#test";
		
		String fromEmail = "pc340355@gmail.com";
		String toEmail = email;

		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "25"); //port Gmail SMTP 25 o 465

		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		//Start message email
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(fromEmail));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			msg.setSubject("prueba");
			
			Multipart content = new MimeMultipart();
			
			//Text body part
			MimeBodyPart bodyContent = new MimeBodyPart();
			bodyContent.setText("Grades!!");
			
			//pdf Attachment
			MimeBodyPart pdfAttacment = new MimeBodyPart();
			pdfAttacment.attachFile(pathFilePDF);
			
			MimeBodyPart txtAttacment = new MimeBodyPart();
			txtAttacment.attachFile(pathFileTxt);
			
			content.addBodyPart(bodyContent);
			content.addBodyPart(pdfAttacment);
			content.addBodyPart(txtAttacment);
			
			//Attach multipart to message
			msg.setContent(content);
			
			Transport.send(msg);
			System.out.println("Report Sent Successfully!!");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
