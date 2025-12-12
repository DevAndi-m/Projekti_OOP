package healthapp.menu;

import healthapp.menu.util.MenuUtil;
import healthapp.model.Doctor;
import healthapp.util.FileUtil;

import java.util.List;
import java.util.Scanner;

public class DoctorMenu {
    private static final Scanner sc = new Scanner(System.in);
    private static List<Doctor> doctors;

    public static void initializeData(List<Doctor> loadedDoctors) {
        doctors = loadedDoctors;
    }

    public static void show() {
        while (true) {
            System.out.println("\n--- MENU DOKTOR ---");
            System.out.println("1 - Shto Doktor");
            System.out.println("2 - Shiko Doktoret");
            System.out.println("3 - Modifiko Doktor");
            System.out.println("4 - Fshij Doktor");
            System.out.println("R - Kthehu");
            System.out.print("Zgjedhja: ");
            String choice = sc.nextLine().toUpperCase();

            switch (choice) {
                case "1" -> addDoctor();
                case "2" -> showDoctors();
                case "3" -> editDoctor();
                case "4" -> deleteDoctor();
                case "R" -> { return; }
                default -> System.out.println("Opsion i pavlefshëm!");
            }
        }
    }

    private static void addDoctor() {
        int id = FileUtil.getNextId(doctors);

        System.out.print("Emri: ");
        String name = sc.nextLine();

        System.out.print("Telefon: ");
        String phone = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Specializimi: ");
        String specialty = sc.nextLine();

        Doctor d = new Doctor(id, name, phone, email, specialty);
        doctors.add(d);
        FileUtil.saveDoctors(doctors);

        System.out.println("Doktori u shtua: " + d);
    }

    private static void showDoctors() {
        if (doctors.isEmpty()) { System.out.println("Nuk ka doktore të regjistruar."); return; }
        for (Doctor d : doctors) System.out.println(d);
    }

    private static void editDoctor() {
        Doctor d = MenuUtil.selectById(doctors, "doktor", id -> FileUtil.findDoctorById(doctors, id));
        if (d == null) return;

        System.out.print("Emri i ri (" + d.getName() + "): "); String name = sc.nextLine();
        if (!name.isEmpty()) d.setName(name);

        System.out.print("Telefon i ri (" + d.getPhone() + "): "); String phone = sc.nextLine();
        if (!phone.isEmpty()) d.setPhone(phone);

        System.out.print("Email i ri (" + d.getEmail() + "): "); String email = sc.nextLine();
        if (!email.isEmpty()) d.setEmail(email);

        System.out.print("Specializimi i ri (" + d.getSpecialty() + "): "); String specialty = sc.nextLine();
        if (!specialty.isEmpty()) d.setSpecialty(specialty);

        FileUtil.saveDoctors(doctors);
        System.out.println("Doktori u ndryshua: " + d);
    }

    private static void deleteDoctor() {
        Doctor d = MenuUtil.selectById(doctors, "doktor", id -> FileUtil.findDoctorById(doctors, id));
        if (d == null) return;

        if (MenuUtil.confirmDelete("doktor")) {
            doctors.remove(d);
            FileUtil.saveDoctors(doctors);
            System.out.println("Doktori u fshi.");
        } else System.out.println("Fshirja u anulua.");
    }
}
