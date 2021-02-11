package ru.kirikomp.arch.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kirikomp.arch.converter.impl.SpecificationConverter;
import ru.kirikomp.arch.dto.SpecificationDto;
import ru.kirikomp.arch.persistence.dao.SpecificationDao;
import ru.kirikomp.arch.persistence.entity.Specification;
import ru.kirikomp.arch.service.NotificationService;
import ru.kirikomp.arch.service.SpecificationService;
import ru.kirikomp.arch.service.impl.notification.NotificationEventEnum;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpecificationServiceImpl implements SpecificationService {

    private final NotificationService notificationService;
    private final SpecificationDao specificationDao;
    private final SpecificationConverter converter;

    @Override
    public SpecificationDto getSpecification(UUID id) {
        Specification spec = specificationDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Specification not found"));
        return converter.convertToDto(spec);
    }

    @Override
    public List<SpecificationDto> getSpecifications(List<UUID> ids) {
        return ids.stream()
                .map(uuid -> specificationDao.findById(uuid).orElse(null))
                .filter(Objects::isNull)
                .collect(Collectors.toList()).stream()
                .map(converter::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SpecificationDto copySpecification(UUID id) throws CloneNotSupportedException {
        SpecificationDto specification = getSpecification(id);
        return createSpecification(specification.clone());
    }

    @Override
    public SpecificationDto createSpecification(SpecificationDto spec) {
        Specification specification = converter.convertToEntity(spec);

        return converter.convertToDto(specificationDao.save(specification));
    }

    @Override
    public SpecificationDto editSpecification(SpecificationDto spec) {
        Specification save = specificationDao.save(converter.convertToEntity(spec));
        SpecificationDto specificationDto = converter.convertToDto(save);
        save.getGroups().forEach(group -> {
            notificationService.notification(NotificationEventEnum.EDIT, group, specificationDto);
        });
        return specificationDto;
    }

    @Override
    public void deleteSpecification(SpecificationDto spec) {
        Specification specification = converter.convertToEntity(spec);
        specificationDao.delete(specification);
        specification.getGroups().forEach(group -> {
            notificationService.notification(NotificationEventEnum.DELETE, group, spec);
        });
    }
}
