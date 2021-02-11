package ru.kirikomp.arch.persistence.nativquerydao;

import ru.kirikomp.arch.persistence.entity.Operation;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface OperationRepository {
    public Operation findById(UUID id) throws SQLException;

    public List<Operation> findAll() throws SQLException;

    public Operation insert(Operation operation) throws SQLException;

    public void deleteById(UUID id) throws SQLException;
}
