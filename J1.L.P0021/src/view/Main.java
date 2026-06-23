package view;

import controller.StudentController;
import service.StudentService;
import utils.Validation;
import static constant.AppConstant.MENU_CREATE;
import static constant.AppConstant.MENU_FIND_SORT;
import static constant.AppConstant.MENU_UPDATE_DELETE;
import static constant.AppConstant.MENU_REPORT;
import static constant.AppConstant.MENU_EXIT;

public class Main {

    public static void main(String[] args) {
        StudentService service = new StudentService();
        StudentController controller = new StudentController(service);

        while (true) {
            displayMenu();
            int choice = Validation.getInt("Your choice: ",
                    "Please enter a number between 1 and 5.",
                    "Invalid input. Please enter a valid number.",
                    MENU_CREATE, MENU_EXIT);
            switch (choice) {
                case MENU_CREATE:
                    controller.createStudent();
                    break;
                case MENU_FIND_SORT:
                    controller.findAndSortStudents();
                    break;
                case MENU_UPDATE_DELETE:
                    controller.updateOrDeleteStudent();
                    break;
                case MENU_REPORT:
                    controller.displayReport();
                    break;
                case MENU_EXIT:
                    System.out.println("Exiting program...");
                    return;
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
        System.out.println("(Please choose 1 to Create, 2 to Find and Sort, 3 to Update/Delete, 4 to Report and 5 to Exit program).");
    }
}
