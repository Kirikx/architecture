package ru.kirikomp.arch.service;

import ru.kirikomp.arch.dto.ProductDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductDto getProduct(UUID id);
    List<ProductDto> getProducts(List<UUID> ids);
    ProductDto createProduct(ProductDto prod);
    ProductDto editProduct(ProductDto prod);
    void deleteProduct(ProductDto prod);
}
