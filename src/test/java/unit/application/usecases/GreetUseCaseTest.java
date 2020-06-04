package unit.application.usecases;

import greetings.application.adapters.clock.TodayDateGenerator;
import greetings.application.adapters.mailer.Mailer;
import greetings.application.adapters.repository.UserListRepository;
import greetings.application.adapters.repository.dtos.UserDTO;
import greetings.application.usecases.GreetUseCase;
import greetings.domain.DayOfYearDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import builders.repository.dtos.UserDTOBuilder;

import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class GreetUseCaseTest {
    private UserDTO aliceDTO;

    @Mock
    private Mailer mailerStub;
    @Mock
    private UserListRepository repository;
    @Mock
    private TodayDateGenerator dateGenerator;

    @BeforeEach
    void returnAlice() {
        aliceDTO = UserDTOBuilder.create().build();
        when(repository.retrieve()).thenReturn(new UserDTO[]{aliceDTO});
    }

    @Test
    void shouldGreetIfBornToday() {
        when(dateGenerator.getTodayDate()).thenReturn(new DayOfYearDate(
                aliceDTO.getBirth().getMonth(),
                aliceDTO.getBirth().getDay()
        ));
        var useCase = new GreetUseCase(mailerStub, repository, dateGenerator);
        useCase.execute();
        var expectedSubject = "Happy birthday";
        var expectedBody = "Happy birthday, dear " + aliceDTO.getName() + "!";
        verify(mailerStub).send(aliceDTO.getMail(), expectedSubject, expectedBody);
    }

    @Test
    void shouldNotGreetIfNotBornToday() {
        when(dateGenerator.getTodayDate()).thenReturn(new DayOfYearDate(
                aliceDTO.getBirth().getMonth(),
                aliceDTO.getBirth().getDay() + 1
        ));
        var useCase = new GreetUseCase(mailerStub, repository, dateGenerator);
        useCase.execute();
        verify(mailerStub, never()).send(any(), any(), any());
    }
}
