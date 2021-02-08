package ru.kirikomp.arch.persistence.nativquerydao;

import ru.kirikomp.arch.persistence.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface UserRepository {
    public User findById(UUID id) throws SQLException;

    public List<User> findAll() throws SQLException;

    public User insert(User user) throws SQLException;

    public void deleteById(UUID id) throws SQLException;
}
