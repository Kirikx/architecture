package ru.kirikomp.arch.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kirikomp.arch.persistence.dao.UserDao;
import ru.kirikomp.arch.persistence.entity.User;
import ru.kirikomp.arch.service.UserService;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public Optional<User> getUser(UUID id) {
        return userDao.findById(id);
    }

    @Override
    public User createUser(User user) {
        return userDao.save(user);
    }

    @Override
    public User updateUser(User user) {
        return createUser(user);
    }

    @Override
    public void daleteUser(UUID id) {
        User user = userDao.findById(id).orElseThrow(() -> new RuntimeException("User " + id + " not found"));
        userDao.delete(user);
    }
}
