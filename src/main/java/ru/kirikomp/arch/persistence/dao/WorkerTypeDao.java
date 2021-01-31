package ru.kirikomp.arch.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kirikomp.arch.persistence.entity.WorkerType;

import java.util.UUID;

@Repository
public interface WorkerTypeDao extends JpaRepository<WorkerType, UUID> {
    WorkerType findByName(String name);
}
