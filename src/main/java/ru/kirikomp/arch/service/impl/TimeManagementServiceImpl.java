package ru.kirikomp.arch.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kirikomp.arch.dto.OperationDto;
import ru.kirikomp.arch.dto.ProductDto;
import ru.kirikomp.arch.dto.SpecificationDto;
import ru.kirikomp.arch.service.ProductService;
import ru.kirikomp.arch.service.SpecificationService;
import ru.kirikomp.arch.service.TimeManagementService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TimeManagementServiceImpl implements TimeManagementService {
    private static final int MARGIN_DAY = 1;
    private final ProductService productService;
    private final SpecificationService specificationService;

    @Override
    public LocalDateTime getTimeProductionSpec(UUID specId) {
        SpecificationDto specification = specificationService.getSpecification(specId);
        LocalDateTime now = LocalDateTime.now();
        Duration duration = specification.getOperations().stream()
                .map(OperationDto::getDurationOperation)
                .reduce((durationOperation, acc) -> acc.plus(durationOperation)).orElseThrow(() -> new RuntimeException("Specification not valid"));
        return now.plus(duration).plusDays(MARGIN_DAY);
    }

    @Override
    public LocalDateTime getTimeProductionProd(UUID prodId) {
        ProductDto product = productService.getProduct(prodId);
        Objects.requireNonNull(product , "Product not found");
        return getTimeProductionSpec(product.getSpecificationId());
    }
}
