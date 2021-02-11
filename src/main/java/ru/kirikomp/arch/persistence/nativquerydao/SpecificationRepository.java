package ru.kirikomp.arch.persistence.nativquerydao;

import ru.kirikomp.arch.persistence.entity.Specification;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface SpecificationRepository {
    public Specification findById(UUID id) throws SQLException;

    public List<Specification> findAll() throws SQLException;

    public Specification insert(Specification specification) throws SQLException;

    public void deleteById(UUID id) throws SQLException;
}
