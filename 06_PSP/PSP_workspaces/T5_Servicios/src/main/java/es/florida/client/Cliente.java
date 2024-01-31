package es.florida.client;

import java.io.UnsupportedEncodingException;
import javax.activation.DataSource;

import java.util.Properties;
import java.util.Scanner;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Cliente {

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Funciona correctamente el envio de mail.
//		Scanner keyboard = new Scanner(System.in);
//		System.out.println("Itroduce la contraseña: ");
//		String mailPass = keyboard.nextLine();
//		String[] emailDestino = {"adprvi@floridauniversitaria.es"};
//		String[] anexo = {"/Users/adrianprietovillena/Pictures/logo_mozal.png"};
//		try {
//			sendMail("Prueba de mensaje", "T5 - Servicios", "adrianprieto95@gmail.com", mailPass,"smtp.gmail.com","587",emailDestino,anexo);
//		} catch (UnsupportedEncodingException | MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
	}
	
	
	public static void sendMail (String messageUser, String subject, String emailSender, String emailSenderPass, String hostEmail, String portEmail, String [] emailDestination, String[] appendix) throws UnsupportedEncodingException, MessagingException {
		
		Properties props = System.getProperties();
		props.put("mail.smtp.host", hostEmail);
		props.put("mail.smtp.user", emailSender);
		props.put("mail.smtp.clave",emailSenderPass);
		props.put("mail.smtp.starttls.enable", "true"); // TLS -> puerto 587
		props.put("mail.smtp.port", portEmail);
		
		Session session = Session.getDefaultInstance(props);
		
		MimeMessage message =  new MimeMessage(session);
		message.setFrom(new InternetAddress(emailSender));
		message.addRecipients(Message.RecipientType.TO, emailDestination[0]);
		message.setSubject(subject);
		
		BodyPart messageBodyPart1 =  new MimeBodyPart();
		messageBodyPart1.setText(messageUser);
		
		BodyPart messageBodyPart2 = new MimeBodyPart();
		DataSource src = new FileDataSource(appendix[0]);
		messageBodyPart2.setDataHandler(new DataHandler(src));
		messageBodyPart2.setFileName(appendix[0]);
		
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart1);
		multipart.addBodyPart(messageBodyPart2);
		
		message.setContent(multipart);
		
		Transport transport = session.getTransport("smtp");
		transport.connect(hostEmail,emailSender,emailSenderPass);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
		
	}

}
