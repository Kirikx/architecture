package ru.kirikomp.arch.facade;

import ru.kirikomp.arch.dto.ProductDto;
import ru.kirikomp.arch.dto.SpecificationDto;

import java.util.List;
import java.util.UUID;

public interface ManagerFacade {
    ProductDto getProduct(UUID id);
    List<ProductDto> getProducts(List<UUID> ids);

    ProductDto updateProduct(ProductDto prod);
    SpecificationDto updateSpecification(SpecificationDto spec);
}
