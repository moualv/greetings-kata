package integration;

import builders.repository.dtos.UserDTOBuilder;
import greetings.adapters.repository.FileUserListRepository;
import greetings.application.adapters.repository.UserListRepository;
import greetings.application.adapters.repository.dtos.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import utils.UsersFileGenerator;

import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FileUserListRepositoryTest {
    private UserDTO alice;
    private UserDTO bob;
    private UserListRepository repository;

    @BeforeEach
    void beforeEach() throws IOException {
        this.createTwoUsers();
        var filePath = this.generateFile();
        repository = new FileUserListRepository(filePath);
    }

    private void createTwoUsers() {
        alice = UserDTOBuilder.create().build();
        bob = UserDTOBuilder.create().build();
    }

    private String generateFile() throws IOException {
        var path = Files.createTempFile("greetRepoInt", ".txt").toAbsolutePath().toString();
        UsersFileGenerator.fromUsers(path, alice, bob);
        return path;
    }

    @Test
    void shouldRetrieveList() {
        var result = repository.retrieve();
        assertArrayEquals(new UserDTO[]{alice, bob}, result);
    }
}
