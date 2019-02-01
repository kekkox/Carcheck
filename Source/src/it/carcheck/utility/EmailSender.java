package it.carcheck.utility;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

	private static EmailSender instance;
	
	public static EmailSender Begin(String email, String password){
		instance = new EmailSender(email, password);
		return instance;
	}
	
	public static EmailSender GetInstance(){
		if(instance == null)
			instance = new EmailSender();
		
		return instance;
	}
	
	private EmailSender(){
		this("", "");
	}
	
	private EmailSender(String email, String password){
		this.email = email;
		this.password = password;
		
		this.emailProperties = System.getProperties();
		this.emailProperties.setProperty("mail.smtp.starttls.enable", "true");
		this.emailProperties.setProperty("mail.smtp.host", SMTP_HOST);
		this.emailProperties.setProperty("mail.smtp.user", this.email);
		this.emailProperties.setProperty("mail.smtp.password", this.password);
		this.emailProperties.setProperty("mail.smtp.port", SMTP_PORT);
		this.emailProperties.setProperty("mail.smtp.auth", "true");
	}
	
	public boolean SendEmail(String email_subject, String email_message, String addressList)
	{
		try {
			InternetAddress[] mailAddress = InternetAddress.parse(addressList);
			return SendEmail(email_subject, email_message, mailAddress);
		} catch (AddressException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean SendEmail(String email_subject, String email_message, InternetAddress[] address){
		Session session = Session.getInstance(this.emailProperties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });
		
		MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(this.email));
            message.addRecipients(Message.RecipientType.TO, address);
            message.setSubject(email_subject);
            message.setText(email_message);
            
            Transport transport = session.getTransport("smtp");
            transport.connect(SMTP_HOST, this.email, this.password);
            transport.sendMessage(message, message.getAllRecipients());
            
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
	}
	
	private String email;
	private String password;
	
	private Properties emailProperties;
	
	private static final String SMTP_HOST = "smtp.gmail.com";
	private static final String SMTP_PORT = "587";
}
