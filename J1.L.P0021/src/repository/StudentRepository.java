package repository;

import java.util.ArrayList;
import java.util.List;
import entity.Student;

/**
 * Handles data operations for Student entities using an in-memory list.
 */
public class StudentRepository {
    private final List<Student> students;

    public StudentRepository() {
        this.students = new ArrayList<>();
    }

    /**
     * Adds a new student to the list.
     *
     * @param student the student to add
     */
    public void add(Student student) {
        students.add(student);
    }

    /**
     * Returns all students in the list.
     *
     * @return list of all students
     */
    public List<Student> getAll() {
        return students;
    }

    /**
     * Returns the total number of students.
     *
     * @return student count
     */
    public int getTotal() {
        return students.size();
    }

    /**
     * Finds a student by their registration ID.
     *
     * @param id the ID to search for
     * @return matching student, or null if not found
     */
    public Student findById(String id) {
        for (Student student : students) {
            if (student.getId().equalsIgnoreCase(id)) {
                return student;
            }
        }
        return null;
    }

    /**
     * Finds students whose name contains the given keyword (case-insensitive).
     *
     * @param name search keyword
     * @return list of matching students
     */
    public List<Student> findByName(String name) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getStudentName().toLowerCase().contains(name.toLowerCase())) {
                result.add(student);
            }
        }
        return result;
    }

    /**
     * Checks whether a given ID already exists in the list.
     *
     * @param id the ID to check
     * @return true if exists, false otherwise
     */
    public boolean isIdExist(String id) {
        for (Student student : students) {
            if (student.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Replaces a student record with an updated version (matched by ID).
     *
     * @param updatedStudent the student with new data
     */
    public void update(Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equalsIgnoreCase(updatedStudent.getId())) {
                students.set(i, updatedStudent);
                return;
            }
        }
    }

    /**
     * Deletes a student record by ID.
     *
     * @param id the ID to delete
     * @return true if deleted, false if not found
     */
    public boolean delete(String id) {
        return students.removeIf(student -> student.getId().equalsIgnoreCase(id));
    }
}
