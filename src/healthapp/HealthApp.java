package healthapp;

import healthapp.menu.DoctorMenu;
import healthapp.menu.PatientMenu;
import healthapp.menu.AppointmentMenu;
import healthapp.util.FileUtil;
import healthapp.model.Doctor;
import healthapp.model.Patient;
import healthapp.model.Appointment;

import java.util.List;
import java.util.Scanner;

public class HealthApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        List<Doctor> doctors = FileUtil.loadDoctors();
        List<Patient> patients = FileUtil.loadPatients();
        List<Appointment> appointments = FileUtil.loadAppointments(doctors, patients);


        DoctorMenu.initializeData(doctors);
        PatientMenu.initializeData(patients);
        AppointmentMenu.initializeData(appointments, doctors, patients);


        while (true) {
            System.out.println("\nZgjidh opsionin:");
            System.out.println("P - Pacient");
            System.out.println("D - Doktor");
            System.out.println("A - Takim");
            System.out.println("Q - Dil");
            System.out.print("Zgjedhja: ");

            String choice = sc.nextLine().toUpperCase();

            switch (choice) {
                case "P":
                    PatientMenu.show();
                    break;
                case "D":
                    DoctorMenu.show();
                    break;
                case "A":
                    AppointmentMenu.show();
                    break;
                case "Q":
                    System.out.println("Duke dalë nga programi...");
                    return;
                default:
                    System.out.println("Opsion i pavlefshëm.");
            }
        }
    }
}

