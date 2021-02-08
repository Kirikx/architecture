package ru.kirikomp.arch.persistence.nativquerydao;

import ru.kirikomp.arch.persistence.entity.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface ProductRepository {
    public Product findById(UUID id) throws SQLException;

    public List<Product> findAll() throws SQLException;

    public Product insert(Product product) throws SQLException;

    public void deleteById(UUID id) throws SQLException;
}
