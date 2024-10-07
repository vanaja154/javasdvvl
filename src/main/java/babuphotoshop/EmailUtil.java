package babuphotoshop;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailUtil {

	public static void sendEmail(String recipientEmail, String subject, String messageBody) {
	    final String senderEmail = "your-email@gmail.com";
	    final String senderPassword = "your-email-password"; // Use app password if needed

	    Properties props = new Properties();
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "587");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true"); // Enable debug mode

	    Session session = Session.getInstance(props, new Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(senderEmail, senderPassword);
	        }
	    });

	    try {
	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(senderEmail));
	        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
	        message.setSubject(subject);
	        message.setText(messageBody);

	        Transport.send(message);
	        System.out.println("Email sent successfully!");

	    } catch (MessagingException e) {
	        System.out.println("Error while sending email: " + e.getMessage());
	        e.printStackTrace();
	    }
	}

}

