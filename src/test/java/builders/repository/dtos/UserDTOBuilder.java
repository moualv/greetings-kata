package builders.repository.dtos;

import com.github.javafaker.Faker;
import greetings.application.adapters.repository.dtos.BirthDTO;
import greetings.application.adapters.repository.dtos.UserDTO;

public class UserDTOBuilder {
    private BirthDTO birth;
    private String name;
    private String mail;

    private UserDTOBuilder() {
        var faker = new Faker();
        this.birth = BirthDTOBuilder.create().build();
        this.name = faker.name().firstName();
        this.mail = faker.internet().emailAddress();
    }

    public UserDTOBuilder setBirth(BirthDTO birth) {
        this.birth = birth;
        return this;
    }

    public static UserDTOBuilder create() {
        return new UserDTOBuilder();
    }

    public UserDTO build() {
        return new UserDTO(
                this.name,
                this.mail,
                this.birth
        );
    }
}
