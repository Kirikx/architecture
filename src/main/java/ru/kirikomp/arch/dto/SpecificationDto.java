package ru.kirikomp.arch.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class SpecificationDto{

    private UUID id;
    private String name;
    private LocalDateTime dateCreate;
    private List<OperationDto> operations;
    private LocalDateTime timeProduction;


    public SpecificationDto clone(){
        SpecificationDto specificationDto = new SpecificationDto();
        specificationDto.setName(this.name);
        specificationDto.setDateCreate(this.dateCreate);
        specificationDto.setOperations(this.operations.stream().map(OperationDto::clone).collect(Collectors.toList()));
        return specificationDto;
    }
}
