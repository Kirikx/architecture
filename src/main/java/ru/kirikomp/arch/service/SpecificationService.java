package ru.kirikomp.arch.service;

import ru.kirikomp.arch.dto.SpecificationDto;

import java.util.List;
import java.util.UUID;

public interface SpecificationService {
    SpecificationDto getSpecification(UUID id);
    List<SpecificationDto> getSpecifications(List<UUID> ids);
    SpecificationDto createSpecification(SpecificationDto spec);
    SpecificationDto editSpecification(SpecificationDto spec);
    void deleteSpecification(SpecificationDto spec);
    SpecificationDto copySpecification(UUID id) throws CloneNotSupportedException;
}
