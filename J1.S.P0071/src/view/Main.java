package view;

import controller.TaskController;
import utils.Validation;

/**
 * Entry point of the Task Management Program (CCRM).
 * Displays the main menu and handles user navigation
 * through the 4 program options.
 */
public class Main {

    public static int displayMenu() {
        System.out.println("\n========== Task Program ==========");
        System.out.println("1. Add Task");
        System.out.println("2. Delete Task");
        System.out.println("3. Display Task");
        System.out.println("4. Exit");
        System.out.println("==================================");

        return Validation.getInt("Enter your choice (1-4): ",
                "Invalid choice! Please enter 1-4.",
                "Invalid input! Please enter a number.",
                1, 4);
    }

    public static void main(String[] args) {
        TaskController controller = new TaskController();

        System.out.println("========================================");
        System.out.println("   Task Management Program (CCRM)");
        System.out.println("========================================");

        while (true) {
            try {
                int choice = displayMenu();

                switch (choice) {
                    case 1 -> controller.addTask();
                    case 2 -> controller.deleteTask();
                    case 3 -> controller.displayTasks();
                    case 4 -> {
                        System.out.println("\nThank you! Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid option!");
                }
            } catch (NullPointerException ex) {
                System.err.println("NullPointerException: " + ex.getMessage());
            } catch (Exception ex) {
                System.err.println("Error: " + ex.getMessage());
            }
        }
    }
}
