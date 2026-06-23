package controller;

import java.util.List;
import java.util.Map;
import entity.Student;
import service.StudentService;
import utils.Validation;
import static constant.AppConstant.VALID_COURSES;

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
     * Creates new students. Loops until the user chooses to stop after reaching
     * the minimum of 10 students.
     */
    public void createStudent() {
        while (true) {
            String id = Validation.getString("Enter ID: ", "ID cannot be empty.");
            String name;
            Student existingStudent = studentService.findStudentById(id);

            if (existingStudent != null) {
                name = existingStudent.getStudentName();
                System.out.println("Student found: " + name + " (Name autofilled to prevent conflict)");
            } else {
                name = Validation.getString("Enter Student Name: ", "Name cannot be empty.");
            }
            String semester = Validation.getString("Enter Semester: ", "Semester cannot be empty.");
            String course = getCourseInput();
            studentService.addStudent(id, name, semester, course);
            System.out.println("Student course registration added successfully.");
            if (studentService.hasReachedMinStudents()) {
                if (!Validation.confirmYesNo("Do you want to continue (Y/N)? ")) {
                    break;
                }
            }
        }
    }

    /**
     * Searches for students by name and displays the results sorted
     * alphabetically.
     */
    public void findAndSortStudents() {
        String name = Validation.getString("Enter student name to search: ", "Name cannot be empty.");
        try {
            List<Student> result = studentService.findStudentsByName(name);
            System.out.println("Student Name | Semester | Course Name");
            for (Student student : result) {
                System.out.println(student);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Finds a student by ID, then offers update or delete options to the user.
     */
    public void updateOrDeleteStudent() {
        String id = Validation.getString("Enter student ID: ", "ID cannot be empty.");
        try {
            Student student = studentService.findStudentById(id);
            String choice = Validation.getUpdateOrDeleteChoice("Do you want to update (U) or delete (D) student? ");
            if (choice.equalsIgnoreCase("U")) {
                String name = Validation.getString("Enter new Student Name: ", "Name cannot be empty.");
                String semester = Validation.getString("Enter new Semester: ", "Semester cannot be empty.");
                String course = getCourseInput();
                studentService.updateStudent(id, name, semester, course);
                System.out.println("Student updated successfully.");
            } else {
                studentService.deleteStudent(id);
                System.out.println("Student deleted successfully.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Displays a report of each student with total enrollments per course.
     */
    public void displayReport() {
        Map<String, Long> report = studentService.generateReport();
        if (report.isEmpty()) {
            System.out.println("No students in the list.");
            return;
        }
        System.out.println("Student Name | Course | Total");
        for (Map.Entry<String, Long> entry : report.entrySet()) {
            System.out.println(entry.getKey() + " | " + entry.getValue());
        }
    }

    /**
     * Prompts the user to enter a course name and validates it against the
     * allowed list.
     *
     * @return a valid course name (Java, .Net, or C/C++)
     */
    private String getCourseInput() {
        while (true) {
            String course = Validation.getString("Enter Course (Java, .Net, C/C++): ", "Course cannot be empty.");
            for (String valid : VALID_COURSES) {
                if (course.equalsIgnoreCase(valid)) {
                    return valid;
                }
            }
            System.out.println("Invalid course. Please enter Java, .Net, or C/C++.");
        }
    }
}
