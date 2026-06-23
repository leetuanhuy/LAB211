package controller;

import constant.WorkerConstants;
import entity.SalaryHistory;
import enums.SalaryStatus;
import entity.Worker;
import service.WorkerService;
import utils.Validation;

import java.util.List;

/**
 * Controller class handling user interaction for worker operations.
 */
public class WorkerController {

    private final WorkerService workerService;

    public WorkerController() {
        this.workerService = new WorkerService();
    }

    /**
     * Prompts user for worker information and adds a new worker.
     */
    public void addWorker() {
        System.out.println("\n--- Add Worker ---");
        String id = Validation.getString("Enter ID: ", "ID cannot be empty.");
        String name = Validation.getString("Enter Name: ", "Name cannot be empty.");
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
        String workLocation = Validation.getString("Enter Work Location: "
                , "Work location cannot be empty.");

        Worker worker = new Worker(id, name, age, salary, workLocation);
        try {
            if (workerService.addWorker(worker)) {
                System.out.println("Worker added successfully.");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Prompts user for worker code and adjustment amount, then adjusts salary.
     *
     * @param status UP for increase, DOWN for decrease
     */
    public void changeSalary(SalaryStatus status) {
        String label = status == SalaryStatus.UP ? "Increase" : "Decrease";
        System.out.println("------- Up/Down Salary --------");
        String code = Validation.getString("Enter Worker ID: "
                , "ID cannot be empty.");
        double amount = Validation.getDouble(
                "Enter Amount: ",
                "Amount must be greater than "
                + WorkerConstants.MIN_ADJUSTMENT_AMOUNT + ".",
                "Invalid amount format.",
                WorkerConstants.MIN_ADJUSTMENT_AMOUNT,
                Double.MAX_VALUE
        );

        try {
            if (workerService.changeSalary(status, code, amount)) {
                System.out.println("Salary " + label.toLowerCase() + "d successfully.");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Displays all salary adjustment history sorted by worker id.
     */
    public void showInformationSalary() {
        System.out.println("--------Display Information Salary--------");
        List<SalaryHistory> histories = workerService.getInformationSalary();
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
