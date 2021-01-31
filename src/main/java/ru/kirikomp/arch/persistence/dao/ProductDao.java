package ru.kirikomp.arch.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kirikomp.arch.persistence.entity.Product;

import java.util.UUID;

@Repository
public interface ProductDao extends JpaRepository<Product, UUID> {
}
