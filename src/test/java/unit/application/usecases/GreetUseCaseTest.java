package unit.application.usecases;

import greetings.application.adapters.mailer.Mailer;
import greetings.application.adapters.repository.UserListRepository;
import greetings.application.adapters.repository.dtos.UserDTO;
import greetings.application.usecases.GreetUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import unit.builders.repository.dtos.UserDTOBuilder;

import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class GreetUseCaseTest {
    private UserDTO aliceDTO;

    @Mock
    private Mailer mailerStub;
    @Mock
    private UserListRepository repository;

    @BeforeEach
    void beforeEach() {
        aliceDTO = UserDTOBuilder.create().build();
    }

    @Test
    void shouldGreetAlice() {
        var expectedSubject = "Happy birthday";
        var expectedBody = "Happy birthday, dear " + aliceDTO.getName() + "!";
        var useCase = new GreetUseCase(mailerStub, repository);
        when(repository.retrieve()).thenReturn(new UserDTO[]{aliceDTO});
        useCase.execute();
        verify(mailerStub).send(aliceDTO.getMail(), expectedSubject, expectedBody);
    }
}
