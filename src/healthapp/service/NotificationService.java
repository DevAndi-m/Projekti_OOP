package healthapp.service;

@FunctionalInterface
public interface NotificationService {
    void sendNotification(String message);
}
