package healthapp.model;

public class Patient extends Person {
    private int age;

    public Patient(int id, String name, String phone, String email, int age) {
        super(id, name, phone, email);
        this.age = age;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String getDetails() {
        return String.format("Pacienti: %s (ID: %d), Mosha: %d, Telefoni: %s, Email: %s",
                getName(), getId(), age, getPhone(), getEmail());
    }
}
