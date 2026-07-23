package view;

import constant.WorkerConstants;
import controller.WorkerController;
import entity.SalaryHistory;
import enums.SalaryStatus;
import entity.Worker;
import exception.AgeOutOfRangeException;
import exception.DuplicateCodeException;
import exception.InvalidIdException;
import exception.InvalidSalaryException;
import exception.WorkerException;
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
                    addWorker(controller);
                    break;
                case WorkerConstants.OPTION_INCREASE_SALARY:
                    changeSalary(SalaryStatus.UP, controller);
                    break;
                case WorkerConstants.OPTION_DECREASE_SALARY:
                    changeSalary(SalaryStatus.DOWN, controller);
                    break;
                case WorkerConstants.OPTION_SHOW_HISTORY:
                    showInformationSalary(controller);
                    break;
                case WorkerConstants.OPTION_QUIT:
                    System.out.println("Program exited.");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public static void displayMenu() {
        System.out.println("\n======== Worker Management ========");
        System.out.println("1. Add a Worker");
        System.out.println("2. Up salary");
        System.out.println("3. Down salary");
        System.out.println("4. Display Information salary");
        System.out.println("5. Quit");
        System.out.println("===================================");
    }

    public static void addWorker(WorkerController controller) {
        try {
            System.out.println("\n--- Add Worker ---");
            String id = Validation.getString("Enter ID: ", "ID cannot be empty.");
            String name = Validation.getString("Enter Name: ",
                    "Name cannot be empty.");
            int age = Validation.getInt(
                    "Enter Age: ",
                    "Age must be between " + WorkerConstants.MIN_AGE + " and " + WorkerConstants.MAX_AGE + ".",
                    "Invalid age format.",
                    WorkerConstants.MIN_AGE,
                    WorkerConstants.MAX_AGE
            );
            double salary = Validation.getDouble(
                    "Enter Salary: ",
                    "Salary must be greater than " + WorkerConstants.MIN_SALARY + ".",
                    "Invalid salary format.",
                    WorkerConstants.MIN_SALARY,
                    Double.MAX_VALUE
            );
            String workLocation = Validation.getString("Enter Work Location: ",
                    "Work location cannot be empty.");

            controller.addWorker(id, name, age, salary, workLocation);
            System.out.println("Worker added successfully.");
        } catch (DuplicateCodeException e) {
            System.err.println("Code Error: " + e.getMessage());
        } catch (InvalidIdException e) {
            System.err.println("ID Error: " + e.getMessage());
        } catch (AgeOutOfRangeException e) {
            System.err.println("Age Error: " + e.getMessage());
        } catch (InvalidSalaryException e) {
            System.err.println("Salary Error: " + e.getMessage());
        } catch (WorkerException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }

    public static void changeSalary(SalaryStatus status,
            WorkerController controller) {
        String label = status == SalaryStatus.UP ? "Increase" : "Decrease";
        System.out.println("------- Up/Down Salary --------");
        String code = Validation.getString("Enter Worker ID: ",
                "ID cannot be empty.");
        double amount = Validation.getDouble(
                "Enter Amount: ",
                "Amount must be greater than "
                + WorkerConstants.MIN_ADJUSTMENT_AMOUNT + ".",
                "Invalid amount format.",
                WorkerConstants.MIN_ADJUSTMENT_AMOUNT,
                Double.MAX_VALUE
        );

        try {
            controller.changeSalary(status, code, amount);
            System.out.println(
                    "Salary " + label.toLowerCase() + "d successfully.");

        } catch (InvalidSalaryException e) {
            System.err.println("Salary Error: " + e.getMessage());
        } catch (WorkerException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }

    public static void showInformationSalary(WorkerController controller) {
        System.out.println("--------Display Information Salary--------");
        List<SalaryHistory> histories = controller.getInformationSalary();
        if (histories.isEmpty()) {
            System.out.println("No salary adjustment records found.");
            return;
        }
        System.out.printf("%-10s%-20s%-10s%-15s%-10s%-15s%n",
                "ID", "Name", "Age", "Salary", "Status", "Date");
        for (SalaryHistory h : histories) {
            System.out.println(h);
        }

    }
}
