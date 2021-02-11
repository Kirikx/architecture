package ru.kirikomp.arch.service.impl.notification;

import ru.kirikomp.arch.persistence.entity.Group;

import java.util.Set;

public interface EventListener {

    Set<NotificationEventEnum> getTypes();
    void update(Group group, Object obj);
}
