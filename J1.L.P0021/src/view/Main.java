package view;

import service.StudentService;
import utils.Validtion;

/**
 * Main application class for Student Management.
 */
public class Main {

    /**
     * Entry point. Displays menu and routes user choices.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        StudentService service = new StudentService();
        while (true) {
            displayMenu();
            int choice = Validtion.getInt("Your choice: ",
                    "Please enter a number between 1 and 5.",
                    "Invalid input. Please enter a valid number.",
                    1, 5);
            switch (choice) {
                case 1:
                    service.createStudent();
                    break;
                case 2:
                    service.findAndSort();
                    break;
                case 3:
                    service.updateOrDelete();
                    break;
                case 4:
                    service.report();
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    return;
            }
        }
    }

    /**
     * Prints the main menu options to the console.
     */
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
