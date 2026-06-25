package service;

import constant.DoctorConstants;
import entity.Doctor;
import java.util.HashMap;

/**
 * Service class providing business logic for managing doctors. Uses a HashMap
 * to store doctor records with the doctor code as the key.
 */
public class DoctorService {

    private HashMap<String, Doctor> doctorMap;

    public DoctorService() {
        doctorMap = new HashMap<>();
    }

    public HashMap<String, Doctor> getDoctorMap() {
        return doctorMap;
    }

    public void setDoctorMap(HashMap<String, Doctor> doctorMap) {
        this.doctorMap = doctorMap;
    }

    /**
     * Checks if the availability value is valid (>= 0).
     *
     * @param availability the availability value to check
     * @return true if availability is >= 0
     */
    public boolean checkAvailability(int availability) {
        return availability >= DoctorConstants.MIN_AVAILABILITY;
    }

    /**
     * Adds a new doctor to the database.
     *
     * @param doctor the doctor to add
     * @return true if the doctor was added successfully
     * @throws Exception if the database is null, doctor data is null, or the
     *                   code already exists
     */
    public boolean addDoctor(Doctor doctor) throws Exception {
        if (doctorMap == null) {
            throw new Exception("Database does not exist");
        }
        if (doctor == null) {
            throw new Exception("Data does not exist");
        }
        if (doctorMap.containsKey(doctor.getCode())) {
            throw new Exception("Doctor code [" + doctor.getCode() + "] is duplicate");
        }
        doctorMap.put(doctor.getCode(), doctor);
        return true;
    }

    /**
     * Updates an existing doctor's information.
     *
     * @param doctor the doctor with updated information
     * @return true if the doctor was updated successfully
     * @throws Exception if the database is null, doctor data is null, or the
     *                   code does not exist
     */
    public boolean updateDoctor(Doctor doctor) throws Exception {
        if (doctorMap == null) {
            throw new Exception("Database does not exist");
        }
        if (doctor == null) {
            throw new Exception("Data doesn't exist");
        }
        if (!doctorMap.containsKey(doctor.getCode())) {
            throw new Exception("Doctor code doesn't exist");
        }
        doctorMap.put(doctor.getCode(), doctor);
        return true;
    }

    /**
     * Deletes a doctor from the database.
     *
     * @param doctor the doctor to delete (code is used to identify)
     * @return true if the doctor was deleted successfully
     * @throws Exception if the database is null, doctor data is null, or the
     *                   code does not exist
     */
    public boolean deleteDoctor(Doctor doctor) throws Exception {
        if (doctorMap == null) {
            throw new Exception("Database does not exist");
        }
        if (doctor == null) {
            throw new Exception("Data doesn't exist");
        }
        if (!doctorMap.containsKey(doctor.getCode())) {
            throw new Exception("Doctor code doesn't exist");
        }
        doctorMap.remove(doctor.getCode());
        return true;
    }

    /**
     * Searches for doctors by a given input string. Matches against code, name,
     * and specialization fields.
     *
     * @param input the search string
     * @return a HashMap of doctors matching the search
     * @throws Exception if the database is null
     */
    public HashMap<String, Doctor> searchDoctor(String input) throws Exception {
        if (doctorMap == null) {
            throw new Exception("Database does not exist");
        }
        HashMap<String, Doctor> result = new HashMap<>();
        for (Doctor doc : doctorMap.values()) {
            if (doc.getCode().contains(input)
                    || doc.getName().contains(input)
                    || doc.getSpecialization().contains(input)) {
                result.put(doc.getCode(), doc);
            }
        }
        return result;
    }
}
