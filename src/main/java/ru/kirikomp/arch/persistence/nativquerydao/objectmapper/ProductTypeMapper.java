package ru.kirikomp.arch.persistence.nativquerydao.objectmapper;

import lombok.RequiredArgsConstructor;
import ru.kirikomp.arch.persistence.entity.ProductType;
import ru.kirikomp.arch.persistence.nativquerydao.ProductTypeRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class ProductTypeMapper implements ProductTypeRepository {

    private static final String SQL_SEARCH = "SELECT id, name FROM product_type WHERE id = ?";
    private static final String SQL_SEARCH_ALL = "SELECT id, name FROM product_type";
    private static final String SQL_INSERT = "INSERT INTO product_type (name) VALUES (?)";
    private static final String SQL_DELETE = "DELETE FROM product_type WHERE id = ?";

    private final Connection connection;

    public ProductType findById(UUID id) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(SQL_SEARCH);
        statement.setString(1, id.toString());
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                ProductType productType = new ProductType();
                productType.setId(UUID.fromString(resultSet.getString(1)));
                productType.setName(resultSet.getString(2));
                return productType;
            }
        }
        return null;
    }

    public List<ProductType> findAll() throws SQLException {
        List<ProductType> productTypes = new ArrayList<>();
        PreparedStatement statement = this.connection.prepareStatement(SQL_SEARCH_ALL);
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                ProductType productType = new ProductType();
                productType.setId(UUID.fromString(resultSet.getString(1)));
                productType.setName(resultSet.getString(2));
                productTypes.add(productType);
            }
        }
        return productTypes;
    }

    public ProductType insert(ProductType productType) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(SQL_INSERT);
        statement.setString(1, productType.getName());
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                ProductType productTypeNew = new ProductType();
                productTypeNew.setId(UUID.fromString(resultSet.getString(1)));
                productTypeNew.setName(resultSet.getString(2));
                return productTypeNew;
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
