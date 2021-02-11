package ru.kirikomp.arch.persistence.nativquerydao.objectmapper;

import lombok.RequiredArgsConstructor;
import ru.kirikomp.arch.persistence.entity.WorkerType;
import ru.kirikomp.arch.persistence.nativquerydao.WorkerTypeRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class WorkerTypeMapper implements WorkerTypeRepository {

    private static final String SQL_SEARCH = "SELECT id, name FROM worker_type WHERE id = ?";
    private static final String SQL_SEARCH_ALL = "SELECT id, name FROM worker_type";
    private static final String SQL_INSERT = "INSERT INTO worker_type (name) VALUES (?)";
    private static final String SQL_DELETE = "DELETE FROM worker_type WHERE id = ?";

    private final Connection connection;

    public WorkerType findById(UUID id) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(SQL_SEARCH);
        statement.setString(1, id.toString());
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                WorkerType workerType = new WorkerType();
                workerType.setId(UUID.fromString(resultSet.getString(1)));
                workerType.setName(resultSet.getString(2));
                return workerType;
            }
        }
        return null;
    }

    public List<WorkerType> findAll() throws SQLException {
        List<WorkerType> workerTypes = new ArrayList<>();
        PreparedStatement statement = this.connection.prepareStatement(SQL_SEARCH_ALL);
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                WorkerType workerType = new WorkerType();
                workerType.setId(UUID.fromString(resultSet.getString(1)));
                workerType.setName(resultSet.getString(2));
                workerTypes.add(workerType);
            }
        }
        return workerTypes;
    }

    public WorkerType insert(WorkerType workerType) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(SQL_INSERT);
        statement.setString(1, workerType.getName());
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                WorkerType workerType1 = new WorkerType();
                workerType1.setId(UUID.fromString(resultSet.getString(1)));
                workerType1.setName(resultSet.getString(2));
                return workerType1;
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
