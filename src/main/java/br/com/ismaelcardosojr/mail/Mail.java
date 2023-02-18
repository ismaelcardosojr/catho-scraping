package br.com.ismaelcardosojr.mail;

import java.util.Properties;

import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;
import jakarta.mail.Authenticator;
import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class Mail {

    private final String SENDER_NAME;
    private final String RECIPIENT_EMAIL;
    private final String FILE_DIRECTORY;
    private final String JOB_SEEKER = "jobseeker.1000DEVs@gmail.com";

    public Mail(String senderName, String recipientEmail, String userDirectory) {
        this.SENDER_NAME = senderName;
        this.RECIPIENT_EMAIL = recipientEmail;
        this.FILE_DIRECTORY = userDirectory + "/Vacancies.csv";
    }

    public void sendEmail() throws MessagingException {
        Properties props = defineProperties();
        Session session = defineSession(props);
        MimeMessage message = buildMessage(session);

        Transport.send(message);
    }

    private Properties defineProperties() {
        Properties props = System.getProperties();

        props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.auth", "true");

        return props;
    }

    private Session defineSession(Properties props) {
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(JOB_SEEKER, "");
            }
        });

        return session;
    }
    
    private MimeMessage buildMessage(Session session) throws MessagingException {
        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(this.JOB_SEEKER));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.RECIPIENT_EMAIL));
        message.setSubject(this.SENDER_NAME + " wants to share these Developer vacancies with you!");

        BodyPart annex = new MimeBodyPart();
        Multipart multi = new MimeMultipart();

        annex.setDataHandler(new DataHandler(new FileDataSource(this.FILE_DIRECTORY)));
        annex.setFileName("Vacancies.csv");
        multi.addBodyPart(annex);

        message.setContent(multi);

        return message;
    }

}