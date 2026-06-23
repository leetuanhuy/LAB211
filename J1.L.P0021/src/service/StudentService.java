package service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import entity.Student;
import static constant.AppConstant.MIN_STUDENTS;

/**
 * Handles business logic for student management. Data is stored in-memory.
 */
public class StudentService {

    private final List<Student> students;

    public StudentService() {
        this.students = new ArrayList<>();
        initSampleData();
    }

    private void initSampleData() {
        students.add(new Student("S01", "Nguyen Van A", "Spring2024", "Java"));
        students.add(new Student("S01", "Nguyen Van A", "Fall2024", ".NET"));
        students.add(new Student("S03", "Nguyen Van B", "Spring2024", ".Net"));
        students.add(new Student("S04", "Nguyen Van B", "Fall2024", "Java"));
        students.add(new Student("S05", "Nguyen Van C", "Spring2024", "Java"));
        students.add(new Student("S01", "Nguyen Van A", "Spring2024", "C#"));
        students.add(new Student("S01", "Nguyen Van A", "Fall2024", "Java"));
        students.add(new Student("S08", "Nguyen Van B", "Spring2024", ".Net"));
        students.add(new Student("S09", "Nguyen Van B", "Fall2024", "Java"));

    }

    /**
     * Adds a new student to the list.
     *
     * @param id student ID
     * @param name student name
     * @param semester semester
     * @param course course name
     * @throws IllegalArgumentException if the ID already exists
     */
    public void addStudent(String id, String name, String semester, String course) {
        
        students.add(new Student(id, name, semester, course));
    }

    /**
     * Searches for students by name (case-insensitive, partial match) and
     * returns the result sorted alphabetically.
     *
     * @param name search keyword
     * @return list of matching students
     * @throws IllegalArgumentException if no student found
     */
    public List<Student> findStudentsByName(String name) {
        List<Student> result = students.stream()
                .filter(s -> s.getStudentName().toLowerCase().contains(name.toLowerCase()))
                .sorted(Comparator.comparing(Student::getStudentName, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
        if (result.isEmpty()) {
            throw new IllegalArgumentException("No student found.");
        }
        return result;
    }

    /**
     * Finds a student by ID (case-insensitive).
     *
     * @param id the student ID
     * @return the matching student
     * @throws IllegalArgumentException if not found
     */
    public Student findStudentById(String id) {
        for (Student s : students) {
            if (s.getId().equalsIgnoreCase(id)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Student not found.");
    }

    /**
     * Updates a student's details by ID. Syncs the new name across all records
     * of the same person (matching old name) to keep report data consistent.
     *
     * @param id student ID
     * @param name new student name
     * @param semester new semester
     * @param course new course name
     * @throws IllegalArgumentException if the ID does not exist
     */
    public void updateStudent(String id, String name, String semester, String course) {     
        Student targetRecord = findStudentById(id);
        if (targetRecord == null) {
            throw new IllegalArgumentException("Student with ID " + id + " not found.");
        }
        students.stream()
                .filter(s -> s.getId().equalsIgnoreCase(id))
                .forEach(s -> s.setStudentName(name));
      
        targetRecord.setSemester(semester);
        targetRecord.setCourseName(course);
    }

    /**
     * Deletes a student by ID.
     *
     * @param id the student ID to delete
     * @throws IllegalArgumentException if the ID does not exist
     */
    public void deleteStudent(String id) {
        if (!students.removeIf(s -> s.getId().equalsIgnoreCase(id))) {
            throw new IllegalArgumentException("Student not found.");
        }
    }

    /**
     * Groups students by name and course, then counts total enrollments.
     *
     * @return map of "name | course" to total count
     */
    public Map<String, Long> generateReport() {
        return students.stream()
                .collect(Collectors.groupingBy(
                        s -> s.getStudentName() + " | " + s.getCourseName(),
                        LinkedHashMap::new,
                        Collectors.counting()));
    }

    /**
     * Checks whether the student list has reached the minimum required count.
     *
     * @return true if total students >= minimum threshold
     */
    public boolean hasReachedMinStudents() {
        return students.size() >= MIN_STUDENTS;
    }

    /**
     * Checks whether a given ID already exists in the list.
     *
     * @param id the ID to check
     * @return true if the ID exists, false otherwise
     */
    private boolean isIdExist(String id) {
        return students.stream().anyMatch(s -> s.getId().equalsIgnoreCase(id));
    }
}
