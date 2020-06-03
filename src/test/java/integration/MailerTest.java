package integration;

import com.dumbster.smtp.ServerOptions;
import com.dumbster.smtp.SmtpServer;
import com.dumbster.smtp.SmtpServerFactory;
import com.github.javafaker.Faker;
import greetings.adapters.SmtpMailer;
import greetings.application.adapters.mailer.Mailer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MailerTest {
    private SmtpServer smtpServerStub;
    private String to;
    private String subject;
    private String body;
    private Mailer mailer;
    private int smtpPort;
    private String smtpMailerFrom;

    @BeforeAll
    void beforeAll() {
        this.startSmtpStub();
        this.setUpMailer();
    }

    private void startSmtpStub() {
        smtpPort = 8119;
        ServerOptions options = new ServerOptions();
        options.port = smtpPort;
        smtpServerStub = SmtpServerFactory.startServer(options);
    }

    private void setUpMailer() {
        var smtpHost = "localhost";
        smtpMailerFrom = new Faker().internet().emailAddress();
        mailer = new SmtpMailer(
                smtpHost,
                smtpPort,
                smtpMailerFrom
        );
    }

    @BeforeEach
    void setUpData() {
        var faker = new Faker();
        to = faker.internet().emailAddress();
        subject = faker.random().toString();
        body = faker.random().toString();
    }

    @Test
    void sendMail() {
        mailer.send(
                to,
                subject,
                body
        );
        assertEquals(1, smtpServerStub.getEmailCount());
        var email = smtpServerStub.getMessage(0);
        var receivedBody = email.getBody();
        var receivedTo = email.getHeaderValues("To")[0];
        var receivedSubject = email.getHeaderValues("Subject")[0];
        assertEquals(body, receivedBody);
        assertEquals(subject, receivedSubject);
        assertEquals(to, receivedTo);
    }
}
