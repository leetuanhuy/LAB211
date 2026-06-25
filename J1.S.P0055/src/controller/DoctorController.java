package controller;

import constant.DoctorConstants;
import entity.Doctor;
import java.util.HashMap;
import service.DoctorService;
import utils.Validation;

/**
 * Controller class that handles user input/output and delegates business logic
 * to the DoctorService.
 */
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController() {
        doctorService = new DoctorService();
    }

    public DoctorService getDoctorService() {
        return doctorService;
    }

    /**
     * Prompts user for new doctor information and adds it to the database.
     */
    public void addDoctor() {
        try {
            String code = Validation.getString("Enter code: ", "Code cannot be empty");
            String name = Validation.getString("Enter name: ", "Name cannot be empty");
            String specialization = Validation.getString("Enter specialization: ", "Specialization cannot be empty");
            int availability = Validation.getInt(
                    "Enter availability: ",
                    "Availability must be >= 0",
                    "Availability must be a number",
                    DoctorConstants.MIN_AVAILABILITY, Integer.MAX_VALUE);
            Doctor doctor = new Doctor(code, name, specialization, availability);
            doctorService.addDoctor(doctor);
            System.out.println("Doctor added successfully!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Prompts user for a doctor code and updated fields, then updates the
     * doctor record. If a field is left blank, the old value is kept.
     */
    public void updateDoctor() {
        try {
            String code = Validation.getString("Enter code: ", "Code cannot be empty");
            Doctor existing = doctorService.getDoctorMap().get(code);
            if (existing == null) {
                throw new Exception("Doctor code doesn't exist");
            }

            String name = Validation.getOptionalString("Enter name: ");
            if (name.isEmpty()) {
                name = existing.getName();
            }

            String specialization = Validation.getOptionalString("Enter specialization: ");
            if (specialization.isEmpty()) {
                specialization = existing.getSpecialization();
            }

            int availability = Validation.getOptionalInt(
                    "Enter availability: ",
                    "Availability must be >= 0",
                    "Availability must be a number",
                    DoctorConstants.MIN_AVAILABILITY, Integer.MAX_VALUE,
                    existing.getAvailability()
            );

            Doctor updated = new Doctor(code, name, specialization, availability);
            doctorService.updateDoctor(updated);
            System.out.println("Doctor updated successfully!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Prompts user for a doctor code and deletes the corresponding record.
     */
    public void deleteDoctor() {
        try {
            String code = Validation.getString("Enter code: ", "Code cannot be empty");
            Doctor doctor = new Doctor();
            doctor.setCode(code);
            doctorService.deleteDoctor(doctor);
            System.out.println("Doctor deleted successfully!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Prompts user for a search string and displays matching doctors.
     */
    public void searchDoctor() {
        try {
            String input = Validation.getString("Enter search string: ", "Search string cannot be empty");
            HashMap<String, Doctor> result = doctorService.searchDoctor(input);
            if (result.isEmpty()) {
                System.out.println("No doctor found matching the search.");
            } else {
                System.out.println("Results:");
                System.out.printf("%-10s %-20s %-20s %-5s%n",
                        "Code", "Name", "Specialization", "Availability");
                for (Doctor doc : result.values()) {
                    System.out.println(doc);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
