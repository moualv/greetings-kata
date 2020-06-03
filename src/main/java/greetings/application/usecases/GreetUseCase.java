package greetings.application.usecases;

import greetings.application.adapters.mailer.Mailer;
import greetings.application.adapters.repository.UserListRepository;

import java.util.Arrays;

public class GreetUseCase {
    private final UserListRepository repository;
    private Mailer mailer;

    public GreetUseCase(Mailer mailer, UserListRepository repository) {
        this.mailer = mailer;
        this.repository = repository;
    }

    public void execute() {
        var userList = Arrays.stream(repository.retrieve());
        userList.forEach(
                userDto -> this.mailer.send(
                        userDto.getMail(),
                        "Happy birthday",
                        this.getGreetingBody(userDto.getName())
                )
        );
    }

    private String getGreetingBody(String name) {
        return "Happy birthday, dear " + name + "!";
    }
}
