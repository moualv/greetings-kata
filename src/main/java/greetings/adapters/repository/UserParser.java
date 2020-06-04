package greetings.adapters.repository;

import greetings.application.adapters.repository.dtos.UserDTO;

public class UserParser {
    public static UserDTO parse(String userStr) {
        var result = userStr.split(",");
        var name = result[0];
        var birth = BirthParser.parse(result[1]);
        var mail = result[2];
        return new UserDTO(
                name,
                mail,
                birth
        );
    }
}
