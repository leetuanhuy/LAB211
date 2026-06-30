package controller;

import constant.AppConstant;
import static constant.AppConstant.VALID_COURSES;
import java.util.List;
import java.util.Map;
import entity.Student;
import service.StudentService;
import utils.Validation;
import static utils.Validation.getString;

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
     */
    public void createStudent() {
        while (true) {
            String id = Validation.getString("Enter ID: ", "ID cannot be empty.");
            String name;
            try {
                List<Student> existing = studentService.findStudentsById(id);
                name = existing.get(AppConstant.FIRST_RECORD_INDEX).getStudentName();
                System.out.println("Student found: " + name + " (Name autofilled to prevent conflict)");
            } catch (IllegalArgumentException e) {
                name = Validation.getString("Enter Student Name: ", "Name cannot be empty.");
            }
            String semester = Validation.getString("Enter Semester: ", "Semester cannot be empty.");
            String course = getCourseInput();
            try {
                studentService.addStudent(id, name, semester, course);
                System.out.println("Student course registration added successfully.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            if (studentService.hasReachedMinStudents()) {
                if (!Validation.confirmYesNo("Do you want to continue (Y/N)? ","","")) {
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
     * Finds all registrations by student ID, displays them as a numbered list,
     * then lets the user choose a specific record to update or delete.
     */
    public void updateOrDeleteStudent() {
        String id = Validation.getString("Enter student ID: ", "ID cannot be empty.");
        try {
            List<Student> records = studentService.findStudentsById(id);
            System.out.println("Index | Student Name | Semester | Course Name");
            for (int i = 0; i < records.size(); i++) {
                Student s = records.get(i);
                System.out.println((i + 1) + ". " + s.getStudentName() + " | " + s.getSemester() + " | " + s.getCourseName());
            }
            int choiceIndex = Validation.getInt("Choose index: ", "Invalid index.", "Invalid number.", 1, records.size()) - 1;
            Student selected = records.get(choiceIndex);
            String action = Validation.getUpdateOrDeleteChoice("Do you want to update (U) or delete (D) student? "
                    ,"","");
            if (action.equalsIgnoreCase("U")) {
                String name = Validation.getString("Enter new Student Name: ", "Name cannot be empty.");
                String semester = Validation.getString("Enter new Semester: ", "Semester cannot be empty.");
                String course = getCourseInput();
                studentService.updateStudent(id, name, semester, course, selected);
                System.out.println("Student updated successfully.");
            } else {
                studentService.deleteStudent(selected);
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
            String displayKey = entry.getKey()
                    .split("#", AppConstant.SPLIT_LIMIT)[AppConstant.DISPLAY_PART_INDEX];
            System.out.println(displayKey + " | " + entry.getValue());
        }
    }

    /**
     * Prompts the user to enter a valid course name from the allowed list.
     *
     * @return a valid course name (Java, .Net, or C/C++)
     */
    public static String getCourseInput() {
        while (true) {
            String course = getString("Enter Course (Java, .Net, C/C++): ", "Course cannot be empty.");
            for (String valid : VALID_COURSES) {
                if (course.equalsIgnoreCase(valid)) {
                    return valid;
                }
            }
            System.out.println("Invalid course. Please enter Java, .Net, or C/C++.");
        }
    }

}
