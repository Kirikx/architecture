package ru.kirikomp.arch.persistence.nativquerydao.identmap;

import ru.kirikomp.arch.persistence.entity.ProductType;
import ru.kirikomp.arch.persistence.nativquerydao.ProductTypeRepository;
import ru.kirikomp.arch.persistence.nativquerydao.objectmapper.ProductTypeMapper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ProductTypeIdentityMap implements ProductTypeRepository {

    private final ProductTypeRepository productTypeMapper = new ProductTypeMapper();
    private static Map productTypeMap = new HashMap();

    private static void addProductType(ProductType productType) {
        productTypeMap.put(productType.getId(), productType);
    }

    private static ProductType getProductType(UUID key) {
        return (ProductType) productTypeMap.get(key);
    }

    private static void deleteProductType(UUID key) {
        productTypeMap.remove(key);
    }

    @Override
    public ProductType findById(UUID id) throws SQLException {
        ProductType productType = null;
        if (productTypeMap.containsKey(id)) {
            productType = getProductType(id);
        } else {
            productType = productTypeMapper.findById(id);
            addProductType(productType);
        }
        return productType;
    }

    @Override
    public List<ProductType> findAll() throws SQLException {
        List<ProductType> all = productTypeMapper.findAll();
        all.forEach(ProductTypeIdentityMap::addProductType);
        return all;
    }

    @Override
    public ProductType insert(ProductType productType) throws SQLException {
        ProductType insert = productTypeMapper.insert(productType);
        addProductType(insert);
        return insert;
    }

    @Override
    public void deleteById(UUID id) throws SQLException {
        productTypeMapper.deleteById(id);
        deleteProductType(id);
    }
}
