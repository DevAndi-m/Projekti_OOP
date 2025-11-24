package healthapp.model;

public class Doctor extends Person {
    private String specialty;

    public Doctor(int id, String name, String phone, String email, String specialty) {
        super(id, name, phone, email);
        this.specialty = specialty;
    }

    // get set, tjerat te trasheguara nga Person
    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @Override
    public String getDetails() {
        return String.format("Doktori: %s (ID: %d), Specializimi: %s, Numri i telefonit: %s, Email: %s",
                getName(),
                getId(),
                specialty,
                getPhone(),
                getEmail()
        );
    }


}