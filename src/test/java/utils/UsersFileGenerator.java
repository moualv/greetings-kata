package utils;

import greetings.adapters.repository.BirthFormatter;
import greetings.application.adapters.repository.dtos.UserDTO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class UsersFileGenerator {

    private static String parseUserToCsv(UserDTO user) {
        return user.getName() + "," + BirthFormatter.format(user.getBirth()) + "," + user.getMail() + "\n";
    }

    public static void fromUsers(String filePath, UserDTO... users) throws IOException {
        var out = Files.newOutputStream(Path.of(filePath), StandardOpenOption.APPEND);
        Arrays.stream(users).forEach(user -> {
            //TODO fix this shit
            try {
                out.write(parseUserToCsv(user).getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        out.close();
    }
}
