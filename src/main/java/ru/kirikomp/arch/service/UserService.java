package ru.kirikomp.arch.service;

import ru.kirikomp.arch.persistence.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    Optional<User> getUser(UUID id);
    User createUser(User user);
    User updateUser(User user);
    void daleteUser(UUID id);
}
