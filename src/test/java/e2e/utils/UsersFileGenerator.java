package e2e.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.sql.Blob;

public class UsersFileGenerator {
    public static String fromGreetTarget(
            UserBuilder user
    ) throws IOException {
        var tmpFile = Files.createTempFile("greetingsTmp", ".txt");
        Files.write(tmpFile.toAbsolutePath(), parseUserToCsv(user).getBytes());
        return tmpFile.toAbsolutePath().toString();
    }

    private static String parseUserToCsv(UserBuilder user) {
        return user.getName() + user.getBirth() + user.getEmail();
    }
}
