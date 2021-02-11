package ru.kirikomp.arch.service;

import ru.kirikomp.arch.dto.SpecificationDto;
import ru.kirikomp.arch.persistence.entity.Group;
import ru.kirikomp.arch.service.impl.notification.EventListener;
import ru.kirikomp.arch.service.impl.notification.NotificationEventEnum;

public interface NotificationService {
    void subscribe(NotificationEventEnum eventType, EventListener listener);
    void unsubscribe(NotificationEventEnum eventType, EventListener listener);
    void notification(NotificationEventEnum type, Group group, SpecificationDto specificationDto);
    void notificationAll();
}
