package ru.kirikomp.arch.persistence.nativquerydao.identmap;

import ru.kirikomp.arch.persistence.entity.User;
import ru.kirikomp.arch.persistence.nativquerydao.UserRepository;
import ru.kirikomp.arch.persistence.nativquerydao.objectmapper.UserMapper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class UserIdentityMap implements UserRepository {

    private final UserRepository userRepository = new UserMapper();
    private static Map userMap = new HashMap();

    private static void addUser(User user) {
        userMap.put(user.getId(), user);
    }

    private static User getUser(UUID key) {
        return (User) userMap.get(key);
    }

    private static void deleteUser(UUID key) {
        userMap.remove(key);
    }

    @Override
    public User findById(UUID id) throws SQLException {
        User user = null;
        if (userMap.containsKey(id)) {
            user = getUser(id);
        } else {
            user = userRepository.findById(id);
            addUser(user);
        }
        return user;
    }

    @Override
    public List<User> findAll() throws SQLException {
        List<User> all = userRepository.findAll();
        all.forEach(UserIdentityMap::addUser);
        return all;
    }

    @Override
    public User insert(User user) throws SQLException {
        User insert = userRepository.insert(user);
        addUser(insert);
        return insert;
    }

    @Override
    public void deleteById(UUID id) throws SQLException {
        userRepository.deleteById(id);
        deleteUser(id);
    }
}
