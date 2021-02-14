package ru.kirikomp.arch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kirikomp.arch.dto.SpecificationDto;
import ru.kirikomp.arch.service.SpecificationService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/specification")
@RequiredArgsConstructor
public class SpecificationController {

    private final SpecificationService specificationService;

    @GetMapping
    public List<SpecificationDto> getSpecs() {
        return specificationService.getSpecifications();
    }

    @GetMapping("/{id}")
    public SpecificationDto getSpec(@RequestParam UUID id) {
        return specificationService.getSpecification(id);
    }

    @PostMapping
    public SpecificationDto createSpec(SpecificationDto specificationDto) {
        return specificationService.createSpecification(specificationDto);
    }

    @DeleteMapping("/{id}")
    public void deleteSpec(@RequestParam UUID id) {
        specificationService.deleteSpecification(id);
    }

    @PostMapping("/update")
    public SpecificationDto updateSpec(SpecificationDto specificationDto) {
        return specificationService.editSpecification(specificationDto);
    }
}
