package ru.kirikomp.arch.persistence.nativquerydao;

import ru.kirikomp.arch.persistence.entity.ProductType;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface ProductTypeRepository {
    public ProductType findById(UUID id) throws SQLException;

    public List<ProductType> findAll() throws SQLException;

    public ProductType insert(ProductType product) throws SQLException;

    public void deleteById(UUID id) throws SQLException;
}
