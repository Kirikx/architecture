package ru.kirikomp.arch.dto;

import lombok.Data;

import java.time.Duration;
import java.util.Objects;
import java.util.UUID;

@Data
public class OperationDto implements Cloneable{

    private UUID id;
    private String name;
    private String description;
    private OperationDto parent;
    private Duration durationOperation;
    private String workerType;

    public OperationDto clone() throws CloneNotSupportedException {
        OperationDto operationDto = (OperationDto) super.clone();
        operationDto.setName(this.name);
        operationDto.setDescription(this.description);
        operationDto.setParent(this.parent);
        operationDto.setDurationOperation(this.durationOperation);
        operationDto.setWorkerType(this.workerType);
        return operationDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationDto that = (OperationDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(parent, that.parent) &&
                Objects.equals(durationOperation, that.durationOperation) &&
                Objects.equals(workerType, that.workerType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, parent, durationOperation, workerType);
    }
}
