package healthapp.service;

public class EmailService implements NotificationService {
    private String receiverEmail;

    public EmailService(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("Duke derguar EMAIL...");
        System.out.println("Te: " + receiverEmail);
        System.out.println("Mesazhi: " + message);
    }
}
