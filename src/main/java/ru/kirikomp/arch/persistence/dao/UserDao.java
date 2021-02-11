package ru.kirikomp.arch.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kirikomp.arch.persistence.entity.Product;
import ru.kirikomp.arch.persistence.entity.User;

import java.util.UUID;

@Repository
public interface UserDao extends JpaRepository<User, UUID> {
}
