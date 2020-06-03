package greetings.application.adapters.repository;

import greetings.application.adapters.repository.dtos.UserDTO;

public interface UserListRepository {
    public UserDTO[] retrieve();
}
