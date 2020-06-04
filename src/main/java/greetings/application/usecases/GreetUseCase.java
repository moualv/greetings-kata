package greetings.application.usecases;

import greetings.application.adapters.clock.TodayDateGenerator;
import greetings.application.adapters.mailer.Mailer;
import greetings.application.adapters.repository.UserListRepository;
import greetings.application.adapters.repository.dtos.UserDTO;
import greetings.domain.DayOfYearDate;
import greetings.domain.User;

import java.util.Arrays;

public class GreetUseCase {
    private final UserListRepository repository;
    private final TodayDateGenerator todayDateGenerator;
    private final Mailer mailer;

    public GreetUseCase(Mailer mailer, UserListRepository repository, TodayDateGenerator dateGenerator) {
        this.mailer = mailer;
        this.repository = repository;
        this.todayDateGenerator = dateGenerator;
    }

    public void execute() {
        var todayDate = todayDateGenerator.getTodayDate();
        Arrays.stream(repository.retrieve())
                .map(this::mapDtoToUser)
                .filter(user -> user.isBirthDay( todayDate ))
                .forEach(this::sendGreet);
    }

    private void sendGreet(User user) {
        this.mailer.send(
                user.getMail(),
                "Happy birthday",
                this.getGreetingBody(user.getName())
        );
    }

    private User mapDtoToUser(UserDTO dto) {
        return new User(
                dto.getName(),
                dto.getMail(),
                new DayOfYearDate(
                        dto.getBirth().getMonth(),
                        dto.getBirth().getDay()
                )
        );
    }

    private String getGreetingBody(String name) {
        return "Happy birthday, dear " + name + "!";
    }
}
