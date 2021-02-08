package ru.kirikomp.arch.persistence.nativquerydao.objectmapper;

import lombok.RequiredArgsConstructor;
import ru.kirikomp.arch.persistence.entity.Group;
import ru.kirikomp.arch.persistence.nativquerydao.GroupRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class GroupMapper implements GroupRepository {

    private static final String SQL_SEARCH = "SELECT id, name FROM group WHERE id = ?";
    private static final String SQL_SEARCH_ALL = "SELECT id, name FROM group";
    private static final String SQL_INSERT = "INSERT INTO group (name) VALUES (?)";
    private static final String SQL_DELETE = "DELETE FROM group WHERE id = ?";

    private final Connection connection;

    public Group findById(UUID id) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(SQL_SEARCH);
        statement.setString(1, id.toString());
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Group group = new Group();
                group.setId(UUID.fromString(resultSet.getString(1)));
                group.setName(resultSet.getString(2));
                return group;
            }
        }
        return null;
    }

    public List<Group> findAll() throws SQLException {
        List<Group> groups = new ArrayList<>();
        PreparedStatement statement = this.connection.prepareStatement(SQL_SEARCH_ALL);
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Group group = new Group();
                group.setId(UUID.fromString(resultSet.getString(1)));
                group.setName(resultSet.getString(2));
                groups.add(group);
            }
        }
        return groups;
    }

    public Group insert(Group group) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(SQL_INSERT);
        statement.setString(1, group.getName());
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Group group_new = new Group();
                group_new.setId(UUID.fromString(resultSet.getString(1)));
                group_new.setName(resultSet.getString(2));
                return group_new;
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
