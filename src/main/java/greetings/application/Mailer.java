package greetings.application;

public interface Mailer {
    public void send(String to, String subject, String body);
}
