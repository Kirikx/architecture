package ru.kirikomp.arch.persistence.nativquerydao.objectmapper;

import lombok.RequiredArgsConstructor;
import ru.kirikomp.arch.persistence.entity.Specification;
import ru.kirikomp.arch.persistence.nativquerydao.SpecificationRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class SpecificationMapper implements SpecificationRepository {

    private static final String SQL_SEARCH = "SELECT id, name, date_create FROM specification WHERE id = ?";
    private static final String SQL_SEARCH_ALL = "SELECT id, name, date_create FROM specification";
    private static final String SQL_INSERT = "INSERT INTO specification (name, date_create) VALUES (?, ?)";
    private static final String SQL_DELETE = "DELETE FROM specification WHERE id = ?";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final Connection connection;

    public Specification findById(UUID id) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(SQL_SEARCH);
        statement.setString(1, id.toString());
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Specification specification = new Specification();
                specification.setId(UUID.fromString(resultSet.getString(1)));
                specification.setName(resultSet.getString(2));
                specification.setDateCreate(LocalDateTime.parse(resultSet.getString(3), formatter));
                return specification;
            }
        }
        return null;
    }

    public List<Specification> findAll() throws SQLException {
        List<Specification> specifications = new ArrayList<>();
        PreparedStatement statement = this.connection.prepareStatement(SQL_SEARCH_ALL);
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Specification specification = new Specification();
                specification.setId(UUID.fromString(resultSet.getString(1)));
                specification.setName(resultSet.getString(2));
                specification.setDateCreate(LocalDateTime.parse(resultSet.getString(3), formatter));
                specifications.add(specification);
            }
        }
        return specifications;
    }

    public Specification insert(Specification specification) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(SQL_INSERT);
        statement.setString(1, specification.getName());
        statement.setString(2, LocalDateTime.now().toString());
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Specification specification1 = new Specification();
                specification1.setId(UUID.fromString(resultSet.getString(1)));
                specification1.setName(resultSet.getString(2));
                specification1.setDateCreate(LocalDateTime.parse(resultSet.getString(3), formatter));
                return specification1;
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
