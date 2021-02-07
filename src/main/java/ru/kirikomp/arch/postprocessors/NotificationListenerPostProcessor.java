package ru.kirikomp.arch.postprocessors;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import ru.kirikomp.arch.service.NotificationService;
import ru.kirikomp.arch.service.impl.notification.EventListener;

@Component
@RequiredArgsConstructor
public class NotificationListenerPostProcessor implements BeanPostProcessor {
    private final NotificationService notificationService;
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof EventListener) {
            EventListener eventListener = (EventListener) bean;
            eventListener.getTypes().forEach(type ->
                notificationService.subscribe(type, eventListener));
        }
        return bean;
    }
}
