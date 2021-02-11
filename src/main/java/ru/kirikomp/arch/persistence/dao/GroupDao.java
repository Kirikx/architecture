package ru.kirikomp.arch.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kirikomp.arch.persistence.entity.Group;
import ru.kirikomp.arch.persistence.entity.User;

import java.util.UUID;

@Repository
public interface GroupDao extends JpaRepository<Group, UUID> {
}
