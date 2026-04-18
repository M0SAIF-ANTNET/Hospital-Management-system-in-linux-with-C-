package src.service;

import java.util.List;
import java.util.stream.Collectors;

import src.enums.NotificationType;
import src.model.Notification;

public class NotificationService {
    private final RestaurantDataStore store;

    public NotificationService(RestaurantDataStore store) {
        this.store = store;
    }

    public void send(String targetId, NotificationType type, String message) {
        store.getNotifications().add(new Notification(targetId, type, message));
    }

    public List<Notification> getByTarget(String targetId) {
        return store.getNotifications().stream().filter(n -> n.getTargetId().equals(targetId)).collect(Collectors.toList());
    }
}
