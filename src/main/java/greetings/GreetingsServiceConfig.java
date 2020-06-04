package greetings;

public class GreetingsServiceConfig {
    private int smtpPort = 25;
    private String smtpHost = "localhost";
    private String usersFilePath = "";

    public void setSmtpPort(int smtpPort) {
        this.smtpPort = smtpPort;
    }

    public int getSmtpPort() {
        return smtpPort;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public String getUsersFilePath() {
        return usersFilePath;
    }

    public void setUsersFilePath(String usersFilePath) {
        this.usersFilePath = usersFilePath;
    }

    public String getFromMail() {
        return "greetingsService@example.com";
    }
}
