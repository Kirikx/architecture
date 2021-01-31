package ru.kirikomp.arch.converter.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kirikomp.arch.converter.ConverterArch;
import ru.kirikomp.arch.dto.ProductDto;
import ru.kirikomp.arch.persistence.dao.ProductTypeDao;
import ru.kirikomp.arch.persistence.dao.SpecificationDao;
import ru.kirikomp.arch.persistence.entity.Product;

@Component
@RequiredArgsConstructor
public class ProductConverter implements ConverterArch<Product, ProductDto> {
    private final ProductTypeDao productTypeDao;
    private final SpecificationDao specificationDao;

    @Override
    public ProductDto convertToDto(Product entity) {
        ProductDto productDto = new ProductDto();
        productDto.setId(entity.getId());
        productDto.setName(entity.getName());
        productDto.setProductType(entity.getProductType().getName());
        productDto.setSpecificationId(entity.getSpecification().getId());
        return productDto;
    }

    @Override
    public Product convertToEntity(ProductDto dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setProductType(productTypeDao.findByName(dto.getProductType()));
        product.setSpecification(specificationDao.findById(dto.getSpecificationId())
                .orElseThrow(() -> new RuntimeException("Specification not found")));
        return product;
    }
}
