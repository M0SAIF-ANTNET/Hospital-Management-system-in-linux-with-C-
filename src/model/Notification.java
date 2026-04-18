package src.model;

import src.enums.NotificationType;

public class Notification {
    private final String targetId;
    private final NotificationType type;
    private final String message;

    public Notification(String targetId, NotificationType type, String message) {
        this.targetId = targetId;
        this.type = type;
        this.message = message;
    }

    public String getTargetId() {
        return targetId;
    }

    public NotificationType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return type + " => " + message;
    }
}
