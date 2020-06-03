package greetings.adapters;

import greetings.application.Mailer;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SmtpMailer implements Mailer {
    private String smtpHost;
    private int smtpPort;
    private String fromMail;

    public SmtpMailer(String smtpHost, int smtpPort, String fromMail) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
        this.fromMail = fromMail;
    }

    @Override
    public void send(String to, String subject, String body) {
        var props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);
        var session = Session.getDefaultInstance(props);
        var message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(fromMail));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            message.setSubject(subject);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            message.setText(body);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
