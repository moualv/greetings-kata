package greetings.domain;

public class User {
    private final String name;
    private final String mail;
    private final DayOfYearDate birthDate;

    public User(String name, String mail, DayOfYearDate birthDate) {
        this.name = name;
        this.mail = mail;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public boolean isBirthDay(DayOfYearDate date) {
        return this.birthDate.equals(date);
    }
}
