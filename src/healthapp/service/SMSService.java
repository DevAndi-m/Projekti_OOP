package healthapp.service;

public class SMSService implements NotificationService {
    private String phoneNumber;

    public SMSService(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("Duke dërguar SMS...");
        System.out.println("Në numrin: " + phoneNumber);
        System.out.println("Mesazhi: " + message);
    }
}
