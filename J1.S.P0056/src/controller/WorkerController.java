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

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    /**
     * Prompts user for worker information and adds a new worker.
     * @param worker
     * @return 
     * @throws java.lang.Exception
     */
    public boolean addWorker(Worker worker) throws Exception{
       return workerService.addWorker(worker);
    }

    /**
     * Prompts user for worker code and adjustment amount, then adjusts salary.
     *
     * @param status UP for increase, DOWN for decrease
     * @param code
     * @param amount
     * @throws java.lang.Exception
     */
    public void changeSalary(SalaryStatus status, String code, double amount) throws Exception{
        return workerService.changeSalary(status, code, amount);
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
