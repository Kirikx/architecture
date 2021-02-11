package ru.kirikomp.arch.converter.impl;

import org.springframework.stereotype.Component;
import ru.kirikomp.arch.converter.ConverterArch;
import ru.kirikomp.arch.dto.SpecificationDto;
import ru.kirikomp.arch.persistence.entity.Specification;

@Component
public class SpecificationConverter implements ConverterArch<Specification, SpecificationDto> {


    @Override
    public SpecificationDto convertToDto(Specification entity) {
        SpecificationDto specificationDto = new SpecificationDto();
        specificationDto.setId(entity.getId());
        specificationDto.setName(entity.getName());
        specificationDto.setDateCreate(entity.getDateCreate());
//        specificationDto.setOperations(entity.getOperations());
        return specificationDto;

    }

    @Override
    public Specification convertToEntity(SpecificationDto dto) {
        Specification specification = new Specification();
        specification.setId(dto.getId());
        specification.setName(dto.getName());
        specification.setDateCreate(dto.getDateCreate());
//        specification.setOperations(dto.getOperations());
        return specification;

    }
}
