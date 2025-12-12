package healthapp.util;

import healthapp.model.Appointment;
import healthapp.model.AppointmentStatus;
import healthapp.model.Doctor;
import healthapp.model.Patient;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileUtil {

    private static final String DOCTOR_FILE = "doctors.txt";
    private static final String PATIENT_FILE = "patients.txt";
    private static final String APPOINTMENT_FILE = "appointments.txt";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static List<Doctor> loadDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        File file = new File(DOCTOR_FILE);
        if (!file.exists()) return doctors;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String phone = parts[2];
                String email = parts[3];
                String specialty = parts[4];
                doctors.add(new Doctor(id, name, phone, email, specialty));
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Gabim gjatë ngarkimit të doktorëve!");
        }
        return doctors;
    }

    public static List<Patient> loadPatients() {
        List<Patient> patients = new ArrayList<>();
        File file = new File(PATIENT_FILE);
        if (!file.exists()) return patients;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String phone = parts[2];
                String email = parts[3];
                int age = Integer.parseInt(parts[4]);
                patients.add(new Patient(id, name, phone, email, age));
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Gabim gjatë ngarkimit të pacientëve!");
        }
        return patients;
    }

    public static List<Appointment> loadAppointments(List<Doctor> doctors, List<Patient> patients) {
        List<Appointment> appointments = new ArrayList<>();
        File file = new File(APPOINTMENT_FILE);
        if (!file.exists()) return appointments;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                int id = Integer.parseInt(parts[0]);
                int patientId = Integer.parseInt(parts[1]);
                int doctorId = Integer.parseInt(parts[2]);
                Date date = sdf.parse(parts[3]);
                String report = parts[4].equals("null") ? null : parts[4];
                AppointmentStatus status = AppointmentStatus.valueOf(parts[5]);

                Patient patient = findPatientById(patients, patientId);
                Doctor doctor = findDoctorById(doctors, doctorId);
                if (patient != null && doctor != null) {
                    Appointment a = new Appointment(id, patient, doctor, date);
                    a.setReport(report);
                    a.setStatus(status);
                    appointments.add(a);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            System.out.println("Gabim gjatë ngarkimit të takimeve!");
        }
        return appointments;
    }

    // ------------------- Save Methods -------------------
    public static void saveDoctors(List<Doctor> doctors) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(DOCTOR_FILE))) {
            for (Doctor d : doctors) {
                pw.println(d.getId() + "|" + d.getName() + "|" + d.getPhone() + "|" +
                        d.getEmail() + "|" + d.getSpecialty());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Gabim gjatë ruajtjes së doktorëve!");
        }
    }

    public static void savePatients(List<Patient> patients) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(PATIENT_FILE))) {
            for (Patient p : patients) {
                pw.println(p.getId() + "|" + p.getName() + "|" + p.getPhone() + "|" +
                        p.getEmail() + "|" + p.getAge());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Gabim gjatë ruajtjes së pacientëve!");
        }
    }

    public static void saveAppointments(List<Appointment> appointments) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(APPOINTMENT_FILE))) {
            for (Appointment a : appointments) {
                pw.println(a.getId() + "|" +
                        a.getPatient().getId() + "|" +
                        a.getDoctor().getId() + "|" +
                        sdf.format(a.getDate()) + "|" +
                        (a.getReport() == null ? "null" : a.getReport()) + "|" +
                        a.getStatus().name());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Gabim gjatë ruajtjes së takimeve!");
        }
    }

    public static Doctor findDoctorById(List<Doctor> doctors, int id) {
        for (Doctor d : doctors) if (d.getId() == id) return d;
        return null;
    }

    public static Patient findPatientById(List<Patient> patients, int id) {
        for (Patient p : patients) if (p.getId() == id) return p;
        return null;
    }

    public static Appointment findAppointmentById(List<Appointment> appointments, int id) {
        for (Appointment a : appointments) if (a.getId() == id) return a;
        return null;
    }

    public static int getNextId(List<?> list) {
        int maxId = 0;
        for (Object obj : list) {
            int id = 0;
            if (obj instanceof Doctor) id = ((Doctor) obj).getId();
            else if (obj instanceof Patient) id = ((Patient) obj).getId();
            else if (obj instanceof Appointment) id = ((Appointment) obj).getId();
            if (id > maxId) maxId = id;
        }
        return maxId + 1;
    }
}
