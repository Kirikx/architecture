package ru.kirikomp.arch.persistence.nativquerydao.identmap;

import ru.kirikomp.arch.persistence.entity.Product;
import ru.kirikomp.arch.persistence.nativquerydao.ProductRepository;
import ru.kirikomp.arch.persistence.nativquerydao.objectmapper.ProductMapper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ProductIdentityMap implements ProductRepository {

    private final ProductRepository productRepository = new ProductMapper();
    private static Map productMap = new HashMap();

    private static void addProduct(Product product) {
        productMap.put(product.getId(), product);
    }

    private static Product getProduct(UUID key) {
        return (Product) productMap.get(key);
    }

    private static void deleteProduct(UUID key) {
        productMap.remove(key);
    }

    @Override
    public Product findById(UUID id) throws SQLException {
        Product product = null;
        if (productMap.containsKey(id)) {
            product = getProduct(id);
        } else {
            product = productRepository.findById(id);
            addProduct(product);
        }
        return product;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> all = productRepository.findAll();
        all.forEach(ProductIdentityMap::addProduct);
        return all;
    }

    @Override
    public Product insert(Product product) throws SQLException {
        Product insert = productRepository.insert(product);
        addProduct(insert);
        return insert;
    }

    @Override
    public void deleteById(UUID id) throws SQLException {
        productRepository.deleteById(id);
        deleteProduct(id);
    }
}
