package ru.kirikomp.arch.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class SpecificationDto implements Cloneable{

    private UUID id;
    private String name;
    private LocalDateTime dateCreate;
    private List<OperationDto> operations;
    private LocalDateTime timeProduction;


    public SpecificationDto clone() throws CloneNotSupportedException {
        SpecificationDto specificationDto = (SpecificationDto) super.clone();
        specificationDto.setName(this.name);
        specificationDto.setDateCreate(this.dateCreate);
        specificationDto.setOperations(this.operations.stream().map(o -> {
            try {
                return o.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList()));
        return specificationDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecificationDto that = (SpecificationDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(dateCreate, that.dateCreate) &&
                Objects.equals(operations, that.operations) &&
                Objects.equals(timeProduction, that.timeProduction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateCreate, operations, timeProduction);
    }
}
