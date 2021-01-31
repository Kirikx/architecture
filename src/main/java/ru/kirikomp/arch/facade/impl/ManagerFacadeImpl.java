package ru.kirikomp.arch.facade.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kirikomp.arch.dto.ProductDto;
import ru.kirikomp.arch.dto.SpecificationDto;
import ru.kirikomp.arch.facade.ManagerFacade;
import ru.kirikomp.arch.service.ProductService;
import ru.kirikomp.arch.service.SpecificationService;
import ru.kirikomp.arch.service.TimeManagementService;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ManagerFacadeImpl implements ManagerFacade {
    private final ProductService productService;
    private final SpecificationService specificationService;
    private final TimeManagementService timeManagementService;

    @Override
    public ProductDto getProduct(UUID id) {
        return productService.getProduct(id);
    }

    @Override
    public List<ProductDto> getProducts(List<UUID> ids) {
        return productService.getProducts(ids);
    }

    @Override
    public ProductDto updateProduct(ProductDto prod) {
        ProductDto productDto;
        if (prod.getId() == null) {
            productDto = productService.createProduct(prod);
        } else {
            productDto = productService.editProduct(prod);
        }
        productDto.setProductionTime(timeManagementService.getTimeProductionProd(productDto.getId()));
        return productDto;
    }

    @Override
    public SpecificationDto updateSpecification(SpecificationDto spec) {
        SpecificationDto specDto;
        if (spec.getId() == null) {
            specDto = specificationService.createSpecification(spec);
        } else {
            specDto = specificationService.editSpecification(spec);
        }
        specDto.setTimeProduction(timeManagementService.getTimeProductionSpec(specDto.getId()));
        return specDto;
    }
}
