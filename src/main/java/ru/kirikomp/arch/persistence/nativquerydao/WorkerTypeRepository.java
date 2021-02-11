package ru.kirikomp.arch.persistence.nativquerydao;

import ru.kirikomp.arch.persistence.entity.WorkerType;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface WorkerTypeRepository {
    public WorkerType findById(UUID id) throws SQLException;

    public List<WorkerType> findAll() throws SQLException;

    public WorkerType insert(WorkerType workerType) throws SQLException;

    public void deleteById(UUID id) throws SQLException;
}
