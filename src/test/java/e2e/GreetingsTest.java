package e2e;

import builders.repository.dtos.BirthDTOBuilder;
import builders.repository.dtos.UserDTOBuilder;
import com.dumbster.smtp.ServerOptions;
import com.dumbster.smtp.SmtpServer;
import com.dumbster.smtp.SmtpServerFactory;
import greetings.GreetingsServiceConfig;
import greetings.application.adapters.repository.dtos.UserDTO;
import utils.UsersFileGenerator;
import greetings.GreetingsService;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GreetingsTest {
    private SmtpServer smtpServerStub;
    private UserDTO userDTO;
    private String usersFile;
    private GreetingsService greetingsService;
    private int smtpPort;

    @BeforeAll
    void beforeAll() throws IOException {
        this.createTmpFile();
        this.startSmtpStub();
    }

    private void createTmpFile() throws IOException {
        usersFile = Files.createTempFile("e2eGreetings", ".txt").toAbsolutePath().toString();
    }

    void startSmtpStub() {
        ServerOptions options = new ServerOptions();
        smtpPort = 8118;
        options.port = smtpPort;
        smtpServerStub = SmtpServerFactory.startServer(options);
    }

    @AfterAll()
    void stopStmpStub() {
        smtpServerStub.stop();
    }

    @BeforeEach
    void beforeEach() throws IOException {
        this.initUsers();
        this.createService();
    }

    void initUsers() throws IOException {
        userDTO = UserDTOBuilder.create().setBirth(
                BirthDTOBuilder.create().birthdayToday().build()
        ).build();
        UsersFileGenerator.fromUsers(
                usersFile,
                userDTO
        );
    }

    void createService() {
        var config = new GreetingsServiceConfig();
        config.setSmtpHost("localhost");
        config.setSmtpPort(smtpPort);
        config.setUsersFilePath(usersFile);
        greetingsService = new GreetingsService(config);
    }

    @Test
    void notifyBithday() {
        greetingsService.greet();
        assertEquals(1, smtpServerStub.getEmailCount());
        var email = smtpServerStub.getMessage(0);
        var expectedBody = "Happy birthday, dear " + userDTO.getName() + "!";
        var expectedSubject = "Happy birthday";
        var expectedTo = userDTO.getMail();
        var receivedBody = email.getBody();
        var receivedTo = email.getHeaderValues("To")[0];
        var receivedSubject = email.getHeaderValues("Subject")[0];
        assertEquals(expectedBody, receivedBody);
        assertEquals(expectedSubject, receivedSubject);
        assertEquals(expectedTo, receivedTo);
    }
}
