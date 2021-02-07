package ru.kirikomp.arch.service.impl.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kirikomp.arch.persistence.dao.GroupDao;
import ru.kirikomp.arch.persistence.entity.Group;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EmailNotificationListener implements EventListener {
    private final GroupDao groupDao;

    @Override
    public Set<NotificationEventEnum> getTypes() {
        return Arrays.stream(NotificationEventEnum.values()).collect(Collectors.toSet());
    }

    @Override
    public void update(Group group, Object object) {
        group.getUsers().forEach(user -> {
            send(user.getEmail(), object);
        });
    }

    void send(String email, Object object) {
        System.out.println("Email to " + email + ": Someone has performed EDIT operation with the following object: " + object);
    }
}
