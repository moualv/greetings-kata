package e2e.utils;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserBuilder {
    private String birth;
    private String name;
    private String email;

    private UserBuilder(String birth, String name, String email) {
        this.birth = birth;
        this.name = name;
        this.email = email;
    }

    public static UserBuilder birthdayToday() {
        var faker = new Faker();
        var birth = LocalDate.now().minusYears(20).format(
            DateTimeFormatter.ofPattern("yyyy/MM/dd")
        );
        var name = faker.name().firstName();
        var mail = faker.internet().emailAddress();
        return new UserBuilder( birth, name, mail );
    }

    public String getEmail() {
        return email;
    }

    public String getBirth() {
        return birth;
    }

    public String getName() {
        return name;
    }
}
