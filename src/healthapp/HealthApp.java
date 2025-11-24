package healthapp;

import healthapp.model.Appointment;
import healthapp.model.AppointmentStatus;
import healthapp.model.Doctor;
import healthapp.model.Patient;
import healthapp.service.EmailService;
import healthapp.service.NotificationService;

import java.util.Scanner;

public class HealthApp {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        while (true){
            System.out.println("Zgjidh opsionin: ");
            System.out.println("P - Pacient");
            System.out.println("D - Doktor");
            System.out.println("A - Takim");
            System.out.println("Q - Dil");

            String choice = sc.nextLine().toUpperCase();

            switch (choice){
                case "P":
//                    patientMenu();
                    break;
                case "D":
//                    doctorMenu();
                    break;
                case "A":
//                    appointmentMenu();
                    break;
                case "Q":
                    System.exit(0);
                default:
                    System.out.println("Opsion i pavlefshëm, ju lutem zgjedhni njeren nga opsionet e vlefshëm");
            }
        }
    }


    static void addDoctor(){
        System.out.println("ID: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Emri: ");
        String name = sc.nextLine();
        System.out.println("Telefoni: ");
        String phone = sc.nextLine();
        System.out.println("Email: ");
        String email = sc.nextLine();
        System.out.println("Specializimi: ");
        String specialty = sc.nextLine();

        Doctor d = new Doctor(id, name, phone, email, specialty);

        //ketu duhet te shenohet funksioni qe te ruhet doktori te nje file
        System.out.println("Doktori u regjistrua me sukses");
    }

    static void scheduleAppointment(){
        System.out.println("ID i Takimit: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("ID i Pacientit: ");
        int patientId = Integer.parseInt(sc.nextLine());
        System.out.println("ID i Doktorit: ");
        int doctorId = Integer.parseInt(sc.nextLine());
        System.out.println("Data në formatin (yyyy-mm-dd): ");
        String date = sc.nextLine();

        // pasi te krijohet FileUtil
        //     Patient p = FileUtil.findPatientById(patientId);
        //    Doctor d = FileUtil.findDoctorById(doctorId);

//        Appointment a = new Appointment(id, p, d, date, null, AppointmentStatus.SCHEDULED);

//        FileUtil.saveAppointment(a);
        System.out.println("Takimi u planifikua!");
//        Patient p = FileUtil.findPatientById(patientId);
//        String patientEmail = p.getEmail();
//        NotificationService notificationsService = new EmailService(patientEmail);
//        notificationsService.sendNotification("Takimi u planifikua per " + /* p.getName() */);

    }
    static void browseAppointmentsByPatient() {
        System.out.print("ID Pacienti: ");
        int patientId = Integer.parseInt(sc.nextLine());

//        for (Appointment a : FileUtil.loadAppointments()) {
//            if (a.getPatient().getId() == patientId) {
//                System.out.println(a);
//            }
//        }
    }
//    static void browseAppointmentsByStatus() {
//         System.out.print("Status (SCHEDULED/COMPLETED/CANCELLED): ");
//         String statusStr = sc.nextLine().toUpperCase();
//         AppointmentStatus status = AppointmentStatus.valueOf(statusStr);
//
//         for (Appointment a : FileUtil.loadAppointments()) {
//             if (a.getStatus() == status) {
//                 System.out.println(a);
//             }
//         }
//    }


}
