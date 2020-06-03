package greetings.application.adapters.mailer;

public interface Mailer {
    public void send(String to, String subject, String body);
}
