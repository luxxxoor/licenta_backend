package com.licenta.emm.Service;

import com.licenta.emm.Domain.ORM.Email;
import com.licenta.emm.Exceptions.EmailNotSent;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SmtpService {
    private static final String USERNAME = "al3xdinamo13@gmail.com";
    private static final String PASSWORD = "Parola08";
    private static final String SUBJECT = "MyApp Confirmation Link";

    public void sendMail(final Email emailToSend, final String text) throws EmailNotSent {
        final Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        final Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });

        try {

            final Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailToSend.getEmail()));
            message.setSubject(SUBJECT);
            message.setText(text);

            Transport.send(message);

        } catch (MessagingException e) {
            throw new EmailNotSent();
        }
    }
}
