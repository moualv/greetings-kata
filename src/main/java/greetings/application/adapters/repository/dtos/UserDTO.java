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

    @Override
    public boolean equals(Object o) {
        if (o instanceof UserDTO) {
            var user = (UserDTO)o;
            return user.getMail().equals(mail) &&
                    user.getName().equals(name) &&
                    user.getBirth().equals(birth);
        } else {
            return false;
        }
    }
}

