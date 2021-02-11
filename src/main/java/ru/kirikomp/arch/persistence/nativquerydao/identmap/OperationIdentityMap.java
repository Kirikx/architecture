package ru.kirikomp.arch.persistence.nativquerydao.identmap;

import ru.kirikomp.arch.persistence.entity.Operation;
import ru.kirikomp.arch.persistence.nativquerydao.OperationRepository;
import ru.kirikomp.arch.persistence.nativquerydao.objectmapper.OperationMapper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OperationIdentityMap implements OperationRepository {

    private final OperationRepository operationRepository = new OperationMapper();
    private static Map operationMap = new HashMap();

    private static void addOperation(Operation operation) {
        operationMap.put(operation.getId(), operation);
    }

    private static Operation getOperation(UUID key) {
        return (Operation) operationMap.get(key);
    }

    private static void deleteOperation(UUID key) {
        operationMap.remove(key);
    }

    @Override
    public Operation findById(UUID id) throws SQLException {
        Operation operation = null;
        if (operationMap.containsKey(id)) {
            operation = getOperation(id);
        } else {
            operation = operationRepository.findById(id);
            addOperation(operation);
        }
        return operation;
    }

    @Override
    public List<Operation> findAll() throws SQLException {
        List<Operation> all = operationRepository.findAll();
        all.forEach(OperationIdentityMap::addOperation);
        return all;
    }

    @Override
    public Operation insert(Operation operation) throws SQLException {
        Operation insert = operationRepository.insert(operation);
        addOperation(insert);
        return insert;
    }

    @Override
    public void deleteById(UUID id) throws SQLException {
        operationRepository.deleteById(id);
        deleteOperation(id);
    }
}
