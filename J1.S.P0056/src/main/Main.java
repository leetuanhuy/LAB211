package main;

import constant.WorkerConstants;
import controller.WorkerController;
import entity.SalaryHistory;
import enums.SalaryStatus;
import entity.Worker;
import service.WorkerService;
import utils.Validation;
import java.util.ArrayList;
import java.util.List;

/**
 * Main entry point for the Worker Management program (J1.S.P0056).
 */
public class Main {

    /**
     * Starts the worker management application.
     *
     * @param args the command line arguments (not used)
     */
    public static void main(String[] args) {
        List<Worker> workerList = new ArrayList<>();
        List<SalaryHistory> salaryHistoryList = new ArrayList<>();
        WorkerService service = new WorkerService(workerList, salaryHistoryList);
        WorkerController controller = new WorkerController(service);
        while (true) {
            displayMenu();
            int option = Validation.getInt(
                    "Enter your choice: ",
                    "Option must be between "
                    + WorkerConstants.MENU_MIN
                    + " and "
                    + WorkerConstants.MENU_MAX + ".",
                    "Invalid input. Please enter a number.",
                    WorkerConstants.MENU_MIN,
                    WorkerConstants.MENU_MAX
            );

            switch (option) {
                case WorkerConstants.OPTION_ADD_WORKER:
                    controller.addWorker();
                    break;
                case WorkerConstants.OPTION_INCREASE_SALARY:
                    controller.changeSalary(SalaryStatus.UP);
                    break;
                case WorkerConstants.OPTION_DECREASE_SALARY:
                    controller.changeSalary(SalaryStatus.DOWN);
                    break;
                case WorkerConstants.OPTION_SHOW_HISTORY:
                    controller.showInformationSalary();
                    break;
                case WorkerConstants.OPTION_QUIT:
                    System.out.println("Program exited.");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    /**
     * Displays the main menu and handles user option selection.
     */
    /**
     * Prints the main menu options.
     */
    public static void displayMenu() {
        System.out.println("\n======== Worker Management ========");
        System.out.println("1. Add a Worker");
        System.out.println("2. Up salary");
        System.out.println("3. Down salary");
        System.out.println("4. Display Information salary");
        System.out.println("5. Quit");
        System.out.println("===================================");
    }
}
