package ru.kirikomp.arch.persistence.nativquerydao.objectmapper;

import lombok.RequiredArgsConstructor;
import ru.kirikomp.arch.persistence.entity.Operation;
import ru.kirikomp.arch.persistence.entity.WorkerType;
import ru.kirikomp.arch.persistence.nativquerydao.OperationRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class OperationMapper implements OperationRepository {

    private static final String SQL_SEARCH = "SELECT id, name, description, worker_type, duration_operation FROM operation WHERE id = ?";
    private static final String SQL_SEARCH_ALL = "SELECT id, name, description, worker_type, duration_operation FROM operation";
    private static final String SQL_INSERT = "INSERT INTO operation (name, description, worker_type, duration_operation) VALUES (?, ?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM operation WHERE id = ?";

    private final Connection connection;

    public Operation findById(UUID id) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(SQL_SEARCH);
        statement.setString(1, id.toString());
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Operation operation = new Operation();
                operation.setId(UUID.fromString(resultSet.getString(1)));
                operation.setName(resultSet.getString(2));
                operation.setDescription(resultSet.getString(3));
                operation.setWorkerType(new WorkerType(UUID.fromString(resultSet.getString(4))));
                operation.setDurationOperation(Duration.ofSeconds(resultSet.getLong(5)));
                return operation;
            }
        }
        return null;
    }

    public List<Operation> findAll() throws SQLException {
        List<Operation> users = new ArrayList<>();
        PreparedStatement statement = this.connection.prepareStatement(SQL_SEARCH_ALL);
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Operation operation = new Operation();
                operation.setId(UUID.fromString(resultSet.getString(1)));
                operation.setName(resultSet.getString(2));
                operation.setDescription(resultSet.getString(3));
                operation.setWorkerType(new WorkerType(UUID.fromString(resultSet.getString(4))));
                operation.setDurationOperation(Duration.ofSeconds(resultSet.getLong(5)));
                users.add(operation);
            }
        }
        return users;
    }

    public Operation insert(Operation operation) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(SQL_INSERT);
        statement.setString(1, operation.getName());
        statement.setString(2, operation.getDescription());
        statement.setString(3, operation.getWorkerType().getId().toString());
        statement.setLong(4, operation.getDurationOperation().getSeconds());
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Operation operation1 = new Operation();
                operation1.setId(UUID.fromString(resultSet.getString(1)));
                operation1.setName(resultSet.getString(2));
                operation1.setDescription(resultSet.getString(3));
                operation1.setWorkerType(new WorkerType(UUID.fromString(resultSet.getString(4))));
                operation1.setDurationOperation(Duration.ofSeconds(resultSet.getLong(5)));
                return operation1;
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
