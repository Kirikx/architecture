package ru.kirikomp.arch.persistence.nativquerydao.objectmapper;

import lombok.RequiredArgsConstructor;
import ru.kirikomp.arch.persistence.entity.User;
import ru.kirikomp.arch.persistence.nativquerydao.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class UserMapper implements UserRepository {

    private static final String SQL_SEARCH = "SELECT id, first_name, midle_name, last_name, email FROM user WHERE id = ?";
    private static final String SQL_SEARCH_ALL = "SELECT id, first_name, midle_name, last_name, email FROM user";
    private static final String SQL_INSERT = "INSERT INTO user (first_name, midle_name, last_name, email) VALUES (?, ?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM user WHERE id = ?";

    private final Connection connection;

    public User findById(UUID id) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(SQL_SEARCH);
        statement.setString(1, id.toString());
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(UUID.fromString(resultSet.getString(1)));
                user.setFirstName(resultSet.getString(2));
                user.setMidleName(resultSet.getString(3));
                user.setLastName(resultSet.getString(4));
                user.setEmail(resultSet.getString(5));
                return user;
            }
        }
        return null;
    }

    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();
        PreparedStatement statement = this.connection.prepareStatement(SQL_SEARCH_ALL);
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(UUID.fromString(resultSet.getString(1)));
                user.setFirstName(resultSet.getString(2));
                user.setMidleName(resultSet.getString(3));
                user.setLastName(resultSet.getString(4));
                user.setEmail(resultSet.getString(5));
                users.add(user);
            }
        }
        return users;
    }

    public User insert(User user) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(SQL_INSERT);
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getMidleName());
        statement.setString(3, user.getLastName());
        statement.setString(4, user.getEmail());
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                User userNew = new User();
                userNew.setId(UUID.fromString(resultSet.getString(1)));
                userNew.setFirstName(resultSet.getString(2));
                userNew.setMidleName(resultSet.getString(3));
                userNew.setLastName(resultSet.getString(4));
                userNew.setEmail(resultSet.getString(5));
                return userNew;
            }
        }
        return null;
    }

    public void deleteById(UUID id) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(SQL_DELETE);
        statement.setString(1, id.toString());
        statement.execute();
    }
}
