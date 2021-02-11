package ru.kirikomp.arch.persistence.nativquerydao;

import ru.kirikomp.arch.persistence.entity.Group;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface GroupRepository {
    public Group findById(UUID id) throws SQLException;

    public List<Group> findAll() throws SQLException;

    public Group insert(Group group) throws SQLException;

    public void deleteById(UUID id) throws SQLException;
}
