package greetings.application.adapters.repository.dtos;

public class UserDTO {
    private String name;
    private String mail;
    private BirthDTO birth;

    public UserDTO(
            String name,
            String mail,
            BirthDTO birth
    ) {
        this.name = name;
        this.mail = mail;
        this.birth = birth;
    }

    public String getName() {
        return this.name;
    }

    public String getMail() {
        return this.mail;
    }

    public BirthDTO getBirth() {
        return this.birth;
    }
}

