package main;

import constant.DoctorConstants;
import controller.DoctorController;
import utils.Validation;

/**
 * Main entry point for the Doctor Management program. Displays the menu and
 * handles user interaction, delegating operations to DoctorController.
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DoctorController controller = new DoctorController();
        while (true) {
            displayMenu();
            int choice = Validation.getInt(
                    "Enter your choice: ",
                    "Choice must be from " + DoctorConstants.MIN_MENU + " to " + DoctorConstants.MAX_MENU,
                    "Choice must be a number",
                    DoctorConstants.MIN_MENU,
                    DoctorConstants.MAX_MENU
            );
            switch (choice) {
                case DoctorConstants.ADD_DOCTOR:
                    controller.addDoctor();
                    break;
                case DoctorConstants.UPDATE_DOCTOR:
                    controller.updateDoctor();
                    break;
                case DoctorConstants.DELETE_DOCTOR:
                    controller.deleteDoctor();
                    break;
                case DoctorConstants.SEARCH_DOCTOR:
                    controller.searchDoctor();
                    break;
                case DoctorConstants.EXIT:
                    System.out.println("Goodbye!");
                    return;
            }
        }
    }

    /**
     * Displays the menu options to the console.
     */
    private static void displayMenu() {
        System.out.println("========= Doctor Management ==========");
        System.out.println("1. Add Doctor");
        System.out.println("2. Update Doctor");
        System.out.println("3. Delete Doctor");
        System.out.println("4. Search Doctor");
        System.out.println("5. Exit");
    }
}
