package greetings.adapters.repository;

import greetings.application.adapters.repository.UserListRepository;
import greetings.application.adapters.repository.dtos.UserDTO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class FileUserListRepository implements UserListRepository {
    private final String path;

    public FileUserListRepository(String path) {
        this.path = path;
    }

    @Override
    public UserDTO[] retrieve() {
        Stream<String> lines;
        try {
            lines = Files.lines(Path.of(this.path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stream<UserDTO> users = lines.map(l -> UserParser.parse(l));
        return users.toArray(UserDTO[]::new);
    }
}
