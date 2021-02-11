package ru.kirikomp.arch.service.impl;

import org.springframework.stereotype.Service;
import ru.kirikomp.arch.dto.SpecificationDto;
import ru.kirikomp.arch.persistence.entity.Group;
import ru.kirikomp.arch.service.NotificationService;
import ru.kirikomp.arch.service.impl.notification.EventListener;
import ru.kirikomp.arch.service.impl.notification.NotificationEventEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotificationServiceImpl implements NotificationService {

    Map<NotificationEventEnum, List<EventListener>> listeners = new HashMap<>();

    public NotificationServiceImpl() {
        for (NotificationEventEnum type : NotificationEventEnum.values()) {
            this.listeners.put(type, new ArrayList<>());
        }
    }

    public void subscribe(NotificationEventEnum type, EventListener listener) {
        List<EventListener> users = listeners.get(type);
        users.add(listener);
    }

    public void unsubscribe(NotificationEventEnum type, EventListener listener) {
        List<EventListener> users = listeners.get(type);
        users.remove(listener);
    }

    @Override
    public void notification(NotificationEventEnum type, Group group, SpecificationDto specificationDto) {
        List<EventListener> users = listeners.get(type);
        for (EventListener listener : users) {
            listener.update(group, specificationDto);
        }
    }

    @Override
    public void notificationAll() {
        //pass
    }
}
