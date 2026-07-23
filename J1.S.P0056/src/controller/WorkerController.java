package controller;

import constant.WorkerConstants;
import entity.SalaryHistory;
import enums.SalaryStatus;
import entity.Worker;
import exception.AgeOutOfRangeException;
import exception.DuplicateCodeException;
import exception.InvalidIdException;
import exception.InvalidSalaryException;
import exception.WorkerException;
import service.WorkerService;

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
     * Adds a new worker via service.
     *
     * @param id           worker Id
     * @param name         worker name
     * @param age          worker age
     * @param salary       worker salary
     * @param workLocation worker location
     * @return true if successful
     * @throws AgeOutOfRangeException if the age out of range
     * @throws DuplicateCodeException if a worker with ID already exist
     * @throws InvalidIdException     if the ID is null , empty
     * @throws InvalidSalaryException if the salary is lower than or equal
     *                                minium salary
     */
    public boolean addWorker(String id, String name, int age, double salary,
            String workLocation) throws AgeOutOfRangeException,
            DuplicateCodeException,
            InvalidSalaryException,
            InvalidIdException, WorkerException {
        if (workerService.isExists(id)) {
            throw new DuplicateCodeException(
                    "Worker ID [" + id + "] already exists.");
        }
        if (id == null || id.trim().isEmpty()) {
            throw new InvalidIdException("Worker ID cannot be null or empty.");
        }

        if (age < WorkerConstants.MIN_AGE || age > WorkerConstants.MAX_AGE) {
            throw new AgeOutOfRangeException(
                    "Age must be between " + WorkerConstants.MIN_AGE + " and " + WorkerConstants.MAX_AGE + ".");
        }
        if (salary <= WorkerConstants.MIN_SALARY) {
            throw new InvalidSalaryException(
                    "Salary must be greater than " + WorkerConstants.MIN_SALARY + ".");
        }

        Worker worker = new Worker(id, name, age, salary, workLocation);
        return workerService.addWorker(worker);
    }

    /**
     * Adjusts salary via service
     *
     * @param status UP for increase, DOWN for decrease
     * @param code   worker id
     * @param amount adjustment amount
     * @return true if successful
     * @throws InvalidSalaryException if the salary is lower than or equal
     */
    public boolean changeSalary(SalaryStatus status, String code, double amount)
            throws InvalidSalaryException, WorkerException {
        if (amount <= WorkerConstants.MIN_ADJUSTMENT_AMOUNT) {
            throw new InvalidSalaryException(
                    "Amount must be greater than "
                    + WorkerConstants.MIN_ADJUSTMENT_AMOUNT + ".");
        }
        Worker worker = workerService.findWorkerByCode(code);
        if (worker == null) {
            throw new WorkerException(
                    "Worker with code [" + code + "] does not exist.");
        }
        double oldSalary = worker.getSalary();
        double calculateSalary = (status == SalaryStatus.UP) ? oldSalary + amount : oldSalary - amount;

        if (calculateSalary <= WorkerConstants.MIN_SALARY) {
            throw new InvalidSalaryException(
                    "Action failed. New salary cannot be lower than or equal to "
                    + WorkerConstants.MIN_SALARY + ".");
        }
        return workerService.changeSalary(worker, status, amount);

    }

    /**
     * get all salary adjustment history sorted by worker id.
     *
     * @return list of salary histories
     */
    public List<SalaryHistory> getInformationSalary() {
        return workerService.getInformationSalary();
    }
}
