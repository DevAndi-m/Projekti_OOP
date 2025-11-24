package healthapp.model;
import java.util.Date;
import java.util.Objects;

public class Appointment {
    private int id;
    private Patient patient;
    private Doctor staff;
    private Date date;
    private String report;
    private AppointmentStatus status;

    // kostruktori, raporti pas perfundimit te appointmentStatus
    public Appointment(int id, Patient patient, Doctor staff, Date date) {
        this.id = id;
        this.patient = patient;
        this.staff = staff;
        this.date = date;

        this.status = AppointmentStatus.SCHEDULED;
    }

    // get / set
    public int getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getStaff() {
        return staff;
    }

    public Date getDate() {
        return date;
    }

    public String getReport() {
        return report;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Appointment otherAppointment) {
            return this.id == otherAppointment.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Takimi #%d: Pacienti [%s], Doktori [%s], Data: %s, Statusi: %s, Raporti: %s",
            id,
            patient.getName(),
            staff.getName(),
            date,
            status.getDescription(),
            report == null ? "N/A" : report
        );
    }
}
