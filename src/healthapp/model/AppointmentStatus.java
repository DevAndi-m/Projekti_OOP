package healthapp.model;

public enum AppointmentStatus {
    SCHEDULED("I planifikuar"),
    COMPLETED("I perfunduar"),
    CANCELLED("I Anuluar");

    private final String description;

    AppointmentStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
