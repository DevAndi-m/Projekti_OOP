package healthapp.menu;

import healthapp.menu.util.MenuUtil;
import healthapp.model.Patient;
import healthapp.util.FileUtil;

import java.util.List;
import java.util.Scanner;

public class PatientMenu {
    private static final Scanner sc = new Scanner(System.in);
    private static List<Patient> patients;

    public static void initializeData(List<Patient> loadedPatients) {
        patients = loadedPatients;
    }

    public static void show() {
        while (true) {
            System.out.println("\n--- MENU PACIENT ---");
            System.out.println("1 - Shto Pacient");
            System.out.println("2 - Shiko Pacientët");
            System.out.println("3 - Modifiko Pacient");
            System.out.println("4 - Fshij Pacient");
            System.out.println("R - Kthehu");
            System.out.print("Zgjedhja: ");
            String choice = sc.nextLine().toUpperCase();

            switch (choice) {
                case "1" -> addPatient();
                case "2" -> showPatients();
                case "3" -> editPatient();
                case "4" -> deletePatient();
                case "R" -> { return; }
                default -> System.out.println("Opsion i pavlefshëm!");
            }
        }
    }

    private static void addPatient() {
        int id = FileUtil.getNextId(patients);
        System.out.print("Emri: "); String name = sc.nextLine();
        System.out.print("Telefon: "); String phone = sc.nextLine();
        System.out.print("Email: "); String email = sc.nextLine();
        System.out.print("Mosha: "); int age = Integer.parseInt(sc.nextLine());

        Patient p = new Patient(id, name, phone, email, age);
        patients.add(p);
        FileUtil.savePatients(patients);
        System.out.println("Pacienti u shtua: " + p);
    }

    private static void showPatients() {
        if (patients.isEmpty()) { System.out.println("Nuk ka pacientë të regjistruar."); return; }
        for (Patient p : patients) System.out.println(p);
    }

    private static void editPatient() {
        Patient p = MenuUtil.selectById(patients, "pacient", id -> FileUtil.findPatientById(patients, id));
        if (p == null) return;

        System.out.print("Emri i ri (" + p.getName() + "): "); String name = sc.nextLine();
        if (!name.isEmpty()) p.setName(name);

        System.out.print("Telefon i ri (" + p.getPhone() + "): "); String phone = sc.nextLine();
        if (!phone.isEmpty()) p.setPhone(phone);

        System.out.print("Email i ri (" + p.getEmail() + "): "); String email = sc.nextLine();
        if (!email.isEmpty()) p.setEmail(email);

        System.out.print("Mosha e re (" + p.getAge() + "): "); String ageStr = sc.nextLine();
        if (!ageStr.isEmpty()) p.setAge(Integer.parseInt(ageStr));

        FileUtil.savePatients(patients);
        System.out.println("Pacienti u ndryshua: " + p);
    }

    private static void deletePatient() {
        Patient p = MenuUtil.selectById(patients, "pacient", id -> FileUtil.findPatientById(patients, id));
        if (p == null) return;

        if (MenuUtil.confirmDelete("pacient")) {
            patients.remove(p);
            FileUtil.savePatients(patients);
            System.out.println("Pacienti u fshi.");
        } else System.out.println("Fshirja u anulua.");
    }
}
