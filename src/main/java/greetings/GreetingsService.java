package greetings;

import greetings.adapters.SmtpMailer;
import greetings.adapters.TodayLocalDateGenerator;
import greetings.adapters.repository.FileUserListRepository;
import greetings.application.adapters.clock.TodayDateGenerator;
import greetings.application.usecases.GreetUseCase;

public class GreetingsService {

    private final SmtpMailer mailer;
    private final FileUserListRepository repository;
    private final TodayDateGenerator dateGenerator;


    public GreetingsService(GreetingsServiceConfig config) {
        this.mailer = new SmtpMailer(config.getSmtpHost(), config.getSmtpPort(), config.getFromMail());
        this.repository = new FileUserListRepository(config.getUsersFilePath());
        this.dateGenerator = new TodayLocalDateGenerator();
    }

    public void greet() {
        var useCase = new GreetUseCase(mailer, repository, dateGenerator);
        useCase.execute();
    }
}
