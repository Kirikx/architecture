package ru.kirikomp.arch.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ProductDto {

    private UUID id;
    private String name;
    private String productType;
    private UUID specificationId;
    private LocalDateTime productionTime;
}
