package controller;

import java.util.List;
import java.util.Map;
import entity.Student;
import service.StudentService;

/**
 * Handles console I/O for student management. Delegates business logic to
 * StudentService.
 */
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Creates new course registrations. Auto-fills student name if ID already
     * exists. Loops until the user chooses to stop after reaching the minimum
     * of 10 students.
     *
     * @param id       student ID
     * @param name     student name
     * @param semester semester
     * @param course   course name
     * 
     */
    public void create(String id, String name, String semester,
            String course) throws IllegalArgumentException {
        Student student = new Student(id, name, semester, course);
        studentService.addStudent(student);

    }


    /**
     * Finds all registrations by student ID, displays them as a numbered list,
     * then lets the user choose a specific record to update or delete.
     *
     * @param id           new student ID
     * @param name         new student name
     * @param semester     new semester
     * @param course       new course name
     * @param targetRecord the specific registration record to update
     * @throws java.lang.Exception
     */
    public void update(String id, String name, String semester,
            String course, Student targetRecord) throws Exception {
        Student updateStudent = new Student(id, name, semester, course);
        studentService.updateStudent(updateStudent, targetRecord);

    }

    /**
     * Deletes a specific course registration record via service.
     *
     * @param targetRecord record to delete
     * @throws Exception if record not found
     */
    public void delete(Student targetRecord) throws Exception {

        studentService.deleteStudent(targetRecord);
    }

    /**
     * Generates a student enrollment report via service.
     *
     * @return map of formatted keys to total count
     */
    public Map<String, Long> generateReport() {
        return studentService.generateReport();

    }

    /**
     * Displays a report of each student with total enrollments per course.
     */
    /**
     * Finds all course registrations of a student by ID.
     *
     * @param id student ID
     * @return list of matching registrations
     * @throws Exception if student not found
     */
    public List<Student> findStudentsById(String id) throws Exception {

        return studentService.findStudentsById(id);
    }

    /**
     * Finds students by name sorted alphabetically.
     *
     * @param name student name keyword
     * @return sorted list of matching students
     * @throws Exception if no student found
     */
    public List<Student> findStudentByName(String name) throws Exception {

        return studentService.findStudentsByName(name);
    }

    /**
     * Checks whether minimum required student count is reached.
     *
     * @return true if threshold is met
     */
    public boolean hasReachedMinStudent() {

        return studentService.hasReachedMinStudents();
    }

}
