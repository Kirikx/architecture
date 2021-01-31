package ru.kirikomp.arch.dto;

import lombok.Data;

import java.time.Duration;
import java.util.UUID;

@Data
public class OperationDto{

    private UUID id;
    private String name;
    private String description;
    private OperationDto parent;
    private Duration durationOperation;
    private String workerType;

    public OperationDto clone(){
        OperationDto operationDto = new OperationDto();
        operationDto.setName(this.name);
        operationDto.setDescription(this.description);
        operationDto.setParent(this.parent);
        operationDto.setDurationOperation(this.durationOperation);
        operationDto.setWorkerType(this.workerType);
        return operationDto;
    }
}
