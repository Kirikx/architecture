package ru.kirikomp.arch.persistence.nativquerydao.objectmapper;

import lombok.RequiredArgsConstructor;
import ru.kirikomp.arch.persistence.entity.Product;
import ru.kirikomp.arch.persistence.entity.ProductType;
import ru.kirikomp.arch.persistence.entity.Specification;
import ru.kirikomp.arch.persistence.nativquerydao.ProductRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class ProductMapper implements ProductRepository {

    private static final String SQL_SEARCH = "SELECT id, name, product_type_id, specification_id FROM product WHERE id = ?";
    private static final String SQL_SEARCH_ALL = "SELECT id, name, product_type_id, specification_id FROM product";
    private static final String SQL_INSERT = "INSERT INTO product (name, product_type_id, specification_id) VALUES (?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM product WHERE id = ?";

    private final Connection connection;

    public Product findById(UUID id) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(SQL_SEARCH);
        statement.setString(1, id.toString());
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(UUID.fromString(resultSet.getString(1)));
                product.setName(resultSet.getString(2));
                product.setProductType(new ProductType(UUID.fromString(resultSet.getString(3))));
                product.setSpecification(new Specification(UUID.fromString(resultSet.getString(4))));
                return product;
            }
        }
        return null;
    }

    public List<Product> findAll() throws SQLException {
        List<Product> users = new ArrayList<>();
        PreparedStatement statement = this.connection.prepareStatement(SQL_SEARCH_ALL);
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(UUID.fromString(resultSet.getString(1)));
                product.setName(resultSet.getString(2));
                product.setProductType(new ProductType(UUID.fromString(resultSet.getString(3))));
                product.setSpecification(new Specification(UUID.fromString(resultSet.getString(4))));
                users.add(product);
            }
        }
        return users;
    }

    public Product insert(Product product) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(SQL_INSERT);
        statement.setString(1, product.getName());
        statement.setString(2, product.getProductType().getId().toString());
        statement.setString(3, product.getSpecification().getId().toString());
        ;
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Product product1 = new Product();
                product1.setId(UUID.fromString(resultSet.getString(1)));
                product1.setName(resultSet.getString(2));
                product1.setProductType(new ProductType(UUID.fromString(resultSet.getString(3))));
                product1.setSpecification(new Specification(UUID.fromString(resultSet.getString(4))));
                return product1;
            }
        }
        return null;
    }

    public void deleteById(UUID id) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(SQL_DELETE);
        statement.setString(1, id.toString());
        statement.execute();
    }
}
