package e2e;

import com.dumbster.smtp.SmtpServer;
import com.dumbster.smtp.SmtpServerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BirthdayNotifitationTest {
    SmtpServer smtpServerStub;

    @BeforeAll
    void generateFile() {
    }

    @BeforeAll()
    void startSmtpStub() {
        smtpServerStub = SmtpServerFactory.startServer();
    }

    @AfterAll()
    void stopStmpServer() {
        smtpServerStub.stop();
    }

    @Test
    void notifyBithday() {
        var userFirstName = "alice";
        assertEquals(1, smtpServerStub.getEmailCount());
        var email = smtpServerStub.getMessage(0);
        var expectedBody = "Happy birthday, dear" + userFirstName;
        assertEquals(expectedBody, email.getBody());
        assertEquals("testTo", "testToPls");
    }
}
