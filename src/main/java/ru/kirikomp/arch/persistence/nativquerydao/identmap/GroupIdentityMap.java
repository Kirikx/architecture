package ru.kirikomp.arch.persistence.nativquerydao.identmap;

import ru.kirikomp.arch.persistence.entity.Group;
import ru.kirikomp.arch.persistence.nativquerydao.GroupRepository;
import ru.kirikomp.arch.persistence.nativquerydao.objectmapper.GroupMapper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class GroupIdentityMap implements GroupRepository {

    private final GroupRepository groupRepository = new GroupMapper();
    private static Map groupMap = new HashMap();

    private static void addGroup(Group group) {
        groupMap.put(group.getId(), group);
    }

    private static Group getGroup(UUID key) {
        return (Group) groupMap.get(key);
    }

    private static void deleteGroup(UUID key) {
        groupMap.remove(key);
    }

    @Override
    public Group findById(UUID id) throws SQLException {
        Group group = null;
        if (groupMap.containsKey(id)) {
            group = getGroup(id);
        } else {
            group = groupRepository.findById(id);
            addGroup(group);
        }
        return group;
    }

    @Override
    public List<Group> findAll() throws SQLException {
        List<Group> all = groupRepository.findAll();
        all.forEach(GroupIdentityMap::addGroup);
        return all;
    }

    @Override
    public Group insert(Group group) throws SQLException {
        Group insert = groupRepository.insert(group);
        addGroup(insert);
        return insert;
    }

    @Override
    public void deleteById(UUID id) throws SQLException {
        groupRepository.deleteById(id);
        deleteGroup(id);
    }
}
