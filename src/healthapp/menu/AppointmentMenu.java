package healthapp.menu;

import healthapp.model.Appointment;
import healthapp.model.AppointmentStatus;
import healthapp.model.Doctor;
import healthapp.model.Patient;
import healthapp.util.FileUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AppointmentMenu {
    private static final Scanner sc = new Scanner(System.in);
    private static List<Appointment> appointments;
    private static List<Doctor> doctors;
    private static List<Patient> patients;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static void initializeData(List<Appointment> loadedAppointments, List<Doctor> loadedDoctors, List<Patient> loadedPatients) {
        appointments = loadedAppointments;
        doctors = loadedDoctors;
        patients = loadedPatients;
    }

    public static void show() {
        while (true) {
            System.out.println("\n--- MENU TAKIME ---");
            System.out.println("1 - Shto Takim");
            System.out.println("2 - Shiko Takimet");
            System.out.println("3 - Modifiko Takim");
            System.out.println("4 - Fshij Takim");
            System.out.println("R - Kthehu");
            System.out.print("Zgjedhja: ");
            String choice = sc.nextLine().toUpperCase();

            if (choice.equals("1")) {
                addAppointment();
            } else if (choice.equals("2")) {
                showAppointments();
            } else if (choice.equals("3")) {
                editAppointment();
            } else if (choice.equals("4")) {
                deleteAppointment();
            } else if (choice.equals("R")) {
                return;
            } else {
                System.out.println("Opsion i pavlefshëm!");
            }
        }
    }

    private static void addAppointment() {
        if (patients.isEmpty() || doctors.isEmpty()) {
            System.out.println("Nuk ka pacientë ose doktorë të regjistruar!");
            return;
        }

        int id = FileUtil.getNextId(appointments);

        System.out.println("Zgjidh pacientin:");
        for (Patient p : patients) {
            System.out.println(p.getId() + " - " + p.getName());
        }
        int patientId = Integer.parseInt(sc.nextLine());
        Patient patient = FileUtil.findPatientById(patients, patientId);

        System.out.println("Zgjidh doktorin:");
        for (Doctor d : doctors) {
            System.out.println(d.getId() + " - " + d.getName());
        }
        int doctorId = Integer.parseInt(sc.nextLine());
        Doctor doctor = FileUtil.findDoctorById(doctors, doctorId);

        System.out.print("Shkruaj datën dhe orën (yyyy-MM-dd HH:mm): ");
        Date date;
        try {
            date = sdf.parse(sc.nextLine());
        } catch (ParseException e) {
            System.out.println("Formati i datës nuk është i saktë!");
            return;
        }

        if (date.before(new Date())) {
            System.out.println("Gabim: Data e takimit nuk mund të jetë në të shkuarën!");
            return;
        }

        Appointment a = new Appointment(id, patient, doctor, date);
        appointments.add(a);
        FileUtil.saveAppointments(appointments);
        System.out.println("Takimi u shtua: " + a);
    }

    private static void showAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("Nuk ka takime të regjistruar.");
            return;
        }
        for (Appointment a : appointments) {
            System.out.println(a);
        }
    }

    private static void editAppointment() {
        System.out.print("Shkruaj ID-në e takimit që do të modifikohet: ");
        int id = Integer.parseInt(sc.nextLine());
        Appointment a = FileUtil.findAppointmentById(appointments, id);
        if (a == null) {
            System.out.println("Takimi nuk u gjet!");
            return;
        }

        System.out.println("1 - Ndrysho pacientin");
        System.out.println("2 - Ndrysho doktorin");
        System.out.println("3 - Ndrysho datën/orën");
        System.out.println("4 - Ndrysho raportin");
        System.out.println("5 - Ndrysho statusin");
        System.out.print("Zgjedhja: ");
        String choice = sc.nextLine();

        switch (choice) {
            case "1":
                System.out.println("Zgjidh pacientin:");
                for (Patient p : patients) System.out.println(p.getId() + " - " + p.getName());
                int pid = Integer.parseInt(sc.nextLine());
                a.setPatient(FileUtil.findPatientById(patients, pid));
                break;
            case "2":
                System.out.println("Zgjidh doktorin:");
                for (Doctor d : doctors) System.out.println(d.getId() + " - " + d.getName());
                int did = Integer.parseInt(sc.nextLine());
                a.setStaff(FileUtil.findDoctorById(doctors, did));
                break;
            case "3":
                System.out.print("Shkruaj datën dhe orën e re: ");
                try {
                    Date newDate = sdf.parse(sc.nextLine());
                    if (!newDate.before(new Date())) a.setDate(newDate);
                    else System.out.println("Data nuk mund të jetë në të shkuarën!");
                } catch (ParseException e) {
                    System.out.println("Formati i datës nuk është i saktë!");
                }
                break;
            case "4":
                System.out.print("Shkruaj raportin e ri: ");
                a.setReport(sc.nextLine());
                break;
            case "5":
                System.out.print("Shkruaj statusin (SCHEDULED/COMPLETED/CANCELED): ");
                try {
                    a.setStatus(AppointmentStatus.valueOf(sc.nextLine().toUpperCase()));
                } catch (Exception e) {
                    System.out.println("Status i pavlefshëm!");
                }
                break;
            default:
                System.out.println("Opsion i pavlefshëm!");
        }
        FileUtil.saveAppointments(appointments);
        System.out.println("Takimi u ndryshua: " + a);
    }

    private static void deleteAppointment() {
        System.out.print("Shkruaj ID-në e takimit që do të fshihet: ");
        int id = Integer.parseInt(sc.nextLine());
        Appointment a = FileUtil.findAppointmentById(appointments, id);
        if (a == null) {
            System.out.println("Takimi nuk u gjet!");
            return;
        }

        System.out.print("Jeni të sigurt? (PO/JO): ");
        if (sc.nextLine().equalsIgnoreCase("PO")) {
            appointments.remove(a);
            FileUtil.saveAppointments(appointments);
            System.out.println("Takimi u fshi.");
        } else {
            System.out.println("Fshirja u anulua.");
        }
    }
}
