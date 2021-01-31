package ru.kirikomp.arch.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kirikomp.arch.persistence.entity.ProductType;

import java.util.UUID;

@Repository
public interface ProductTypeDao extends JpaRepository<ProductType, UUID> {
    ProductType findByName(String name);
}
