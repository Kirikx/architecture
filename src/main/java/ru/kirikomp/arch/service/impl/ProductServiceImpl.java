package ru.kirikomp.arch.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kirikomp.arch.dto.ProductDto;
import ru.kirikomp.arch.service.ProductService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    @Override
    public ProductDto getProduct(UUID id) {
        return null;
    }

    @Override
    public List<ProductDto> getProducts(List<UUID> ids) {
        return null;
    }

    @Override
    public ProductDto createProduct(ProductDto prod) {
        return null;
    }

    @Override
    public ProductDto editProduct(ProductDto prod) {
        return null;
    }

    @Override
    public void deleteProduct(ProductDto prod) {

    }
}
