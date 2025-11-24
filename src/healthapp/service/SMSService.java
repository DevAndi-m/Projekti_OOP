package healthapp.service;

public class SMSService implements NotificationService {
    private String phoneNumber;

    public SMSService(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("Duke derguar SMS...");
        System.out.println("Ne numrin: " + phoneNumber);
        System.out.println("Mesazhi: " + message);
    }
}
