package ru.kirikomp.arch.converter.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kirikomp.arch.converter.ConverterArch;
import ru.kirikomp.arch.dto.OperationDto;
import ru.kirikomp.arch.persistence.dao.WorkerTypeDao;
import ru.kirikomp.arch.persistence.entity.Operation;

@Component
@RequiredArgsConstructor
public class OperationConverter implements ConverterArch<Operation, OperationDto> {
    private final WorkerTypeDao workerTypeDao;

    @Override
    public OperationDto convertToDto(Operation entity) {
        OperationDto operationDto = new OperationDto();
        operationDto.setId(entity.getId());
        operationDto.setName(entity.getName());
        operationDto.setDescription(entity.getDescription());
        operationDto.setParent(convertToDto(entity.getParent()));
        operationDto.setDurationOperation(entity.getDurationOperation());
        operationDto.setWorkerType(entity.getWorkerType().getName());
        return operationDto;

    }

    @Override
    public Operation convertToEntity(OperationDto dto) {
        Operation operation = new Operation();
        operation.setId(dto.getId());
        operation.setName(dto.getName());
        operation.setDescription(dto.getDescription());
        operation.setParent(convertToEntity(dto.getParent()));
        operation.setDurationOperation(dto.getDurationOperation());
        operation.setWorkerType(workerTypeDao.findByName(dto.getWorkerType()));
        operation.setSpecifications(null);
        return operation;
    }
}
