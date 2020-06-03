package e2e;

import com.dumbster.smtp.ServerOptions;
import com.dumbster.smtp.SmtpServer;
import com.dumbster.smtp.SmtpServerFactory;
import e2e.utils.UserBuilder;
import e2e.utils.UsersFileGenerator;
import greetings.GreetingsService;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GreetingsTest {
    private SmtpServer smtpServerStub;
    private UserBuilder userBuilder;
    private String usersFile;
    private GreetingsService greetingsService;

    @BeforeAll
    void generateFile() {
    }

    @BeforeAll()
    void startSmtpStub() {
        ServerOptions options = new ServerOptions();
        options.port = 8118;
        smtpServerStub = SmtpServerFactory.startServer(options);
    }

    @AfterAll()
    void stopStmpServer() {
        smtpServerStub.stop();
    }

    @BeforeEach
    void initUsers() throws IOException {
        userBuilder = UserBuilder.birthdayToday();
        usersFile = UsersFileGenerator.fromGreetTarget(
                userBuilder
        );
    }

    @BeforeEach
    void createService() {
        greetingsService = new GreetingsService();
    }

    @Test
    void notifyBithday() {
        greetingsService.greet(usersFile);
        assertEquals(1, smtpServerStub.getEmailCount());
        var email = smtpServerStub.getMessage(0);
        var expectedBody = "Happy birthday, dear " + userBuilder;
        assertEquals(expectedBody, email.getBody());
        assertEquals("testTo", "testToPls");
    }
}
