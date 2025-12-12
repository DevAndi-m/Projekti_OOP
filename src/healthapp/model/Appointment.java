package healthapp.model;
import java.util.Date;
import java.util.Objects;

public class Appointment {
    private int id;
    private Patient patient;
    private Doctor doctor;
    private Date date;
    private String report;
    private AppointmentStatus status;

    // kostruktori, raporti pas perfundimit te appointmentStatus
    public Appointment(int id, Patient patient, Doctor doctor, Date date) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
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

    public Doctor getDoctor() {
        return doctor;
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

    public void setPatient(Patient patient) { this.patient = patient; }
    public void setStaff(Doctor doctor) { this.doctor = doctor; }
    public void setDate(Date date) { this.date = date; }
    public void setReport(String report) { this.report = report; }
    public void setStatus(AppointmentStatus status) { this.status = status; }


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
        return id + "|" +
                patient.getId() + "|" +
                doctor.getId() + "|" +
                date.getTime() + "|" +   // safer than formatting
                (report == null ? "null" : report) + "|" +
                status.name();
    }

}
