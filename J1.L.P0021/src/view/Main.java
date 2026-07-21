package view;

import constant.AppConstant;
import controller.StudentController;
import service.StudentService;
import utils.Validation;
import static constant.AppConstant.MENU_CREATE;
import static constant.AppConstant.MENU_FIND_SORT;
import static constant.AppConstant.MENU_UPDATE_DELETE;
import static constant.AppConstant.MENU_REPORT;
import static constant.AppConstant.MENU_EXIT;
import static constant.AppConstant.VALID_COURSES;
import entity.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static utils.Validation.getString;

public class Main {

    public static void main(String[] args) {
        List<Student> list = new ArrayList();
        StudentService service = new StudentService(list);
        StudentController controller = new StudentController(service);

        while (true) {
            displayMenu();
            int choice = Validation.getInt("Your choice: ",
                    "Please enter a number between 1 and 5.",
                    "Invalid input. Please enter a valid number.",
                    MENU_CREATE, MENU_EXIT);
            switch (choice) {
                case MENU_CREATE -> addStudent(controller);
                case MENU_FIND_SORT -> findAndSortStudents(controller);
                case MENU_UPDATE_DELETE -> updateOrDeleteStudent(controller);
                case MENU_REPORT -> displayReport(controller);
                case MENU_EXIT -> {
                    System.out.println("Exiting program...");
                    return;
                }
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nWELCOME TO STUDENT MANAGEMENT");
        System.out.println("1. Create");
        System.out.println("2. Find and Sort");
        System.out.println("3. Update/Delete");
        System.out.println("4. Report");
        System.out.println("5. Exit");
        System.out.println(
                "(Please choose 1 to Create, 2 to Find and Sort, 3 to Update/Delete, 4 to Report and 5 to Exit program).");
    }

    private static void addStudent(StudentController controller) {
        while (true) {
            String id = Validation.getString("Enter ID: ", "ID cannot be empty.");
            String name;
            try {
                List<Student> existing = controller.findStudentsById(id);
                name = existing.get(AppConstant.FIRST_RECORD_INDEX).getStudentName();
                System.out.println(
                        "Student found: " + name + " (Name autofilled to prevent conflict)");
            } catch (Exception e) {
                name = Validation.getString("Enter Student Name: ",
                        "Name cannot be empty.");
            }
            String semester = Validation.getString("Enter Semester: ",
                    "Semester cannot be empty.");
            String course = getCourseInput();
            try {
                controller.create(id, name, semester, course);
                System.out.println(
                        "Student course registration added successfully.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            
            if (controller.hasReachedMinStudent()) {
                if (!Validation.confirmYesNo("Do you want to continue (Y/N)? ",
                        "", "")) {
                    break;
                }
            }
        }
    }

    private static void updateOrDeleteStudent(StudentController controller) {
        String id = Validation.getString("Enter student ID: ",
                "ID cannot be empty.");
        try {
            List<Student> records = controller.findStudentsById(id);
            System.out.println("Index | Student Name | Semester | Course Name");
            for (int i = 0; i < records.size(); i++) {
                Student s = records.get(i);
                System.out.println(
                        (i + 1) + ". " + s.getStudentName() + " | " + s.getSemester() + " | " + s.getCourseName());
            }
            int choiceIndex = Validation.getInt("Choose index: ",
                    "Invalid index.", "Invalid number.", 1, records.size()) - 1;
            Student selected = records.get(choiceIndex);
            String action = Validation.getUpdateOrDeleteChoice(
                    "Do you want to update (U) or delete (D) student? ",
                    "", "");
            if (action.equalsIgnoreCase("U")) {
                String name = Validation.getString("Enter new Student Name: ",
                        "Name cannot be empty.");
                String semester = Validation.getString("Enter new Semester: ",
                        "Semester cannot be empty.");
                String course = getCourseInput();
                controller.update(id, name, semester, course,
                        selected);
                System.out.println("Student updated successfully.");
            } else {
                controller.delete(selected);
                System.out.println("Student deleted successfully.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static void displayReport(StudentController controller) {
        Map<String, Long> report = controller.generateReport();
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

    private static void findAndSortStudents(StudentController controller) {

        String name = Validation.getString("Enter student name to search: ",
                "Name cannot be empty.");
        try {
            List<Student> result = controller.findStudentByName(name);
            System.out.println("Student Name | Semester | Course Name");
            for (Student student : result) {
                System.out.println(student);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static String getCourseInput() {
        while (true) {
            String course = getString("Enter Course (Java, .Net, C/C++): ",
                    "Course cannot be empty.");
            for (String valid : VALID_COURSES) {
                if (course.equalsIgnoreCase(valid)) {
                    return valid;
                }
            }
            System.out.println(
                    "Invalid course. Please enter Java, .Net, or C/C++.");
        }
    }
}
