package ru.kirikomp.arch.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kirikomp.arch.persistence.entity.Specification;

import java.util.UUID;

@Repository
public interface SpecificationDao extends JpaRepository<Specification, UUID> {
}
