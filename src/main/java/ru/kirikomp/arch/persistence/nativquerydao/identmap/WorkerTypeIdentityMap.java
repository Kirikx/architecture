package ru.kirikomp.arch.persistence.nativquerydao.identmap;

import ru.kirikomp.arch.persistence.entity.WorkerType;
import ru.kirikomp.arch.persistence.nativquerydao.WorkerTypeRepository;
import ru.kirikomp.arch.persistence.nativquerydao.objectmapper.WorkerTypeMapper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class WorkerTypeIdentityMap implements WorkerTypeRepository {

    private final WorkerTypeRepository workerTypeMapper = new WorkerTypeMapper();
    private static Map workerTypeMap = new HashMap();

    private static void addWorkerType(WorkerType workerType) {
        workerTypeMap.put(workerType.getId(), workerType);
    }

    private static WorkerType getWorkerType(UUID key) {
        return (WorkerType) workerTypeMap.get(key);
    }

    private static void deleteWorkerType(UUID key) {
        workerTypeMap.remove(key);
    }

    @Override
    public WorkerType findById(UUID id) throws SQLException {
        WorkerType workerType = null;
        if (workerTypeMap.containsKey(id)) {
            workerType = getWorkerType(id);
        } else {
            workerType = workerTypeMapper.findById(id);
            addWorkerType(workerType);
        }
        return workerType;
    }

    @Override
    public List<WorkerType> findAll() throws SQLException {
        List<WorkerType> all = workerTypeMapper.findAll();
        all.forEach(WorkerTypeIdentityMap::addWorkerType);
        return all;
    }

    @Override
    public WorkerType insert(WorkerType workerType) throws SQLException {
        WorkerType insert = workerTypeMapper.insert(workerType);
        addWorkerType(insert);
        return insert;
    }

    @Override
    public void deleteById(UUID id) throws SQLException {
        workerTypeMapper.deleteById(id);
        deleteWorkerType(id);
    }
}
