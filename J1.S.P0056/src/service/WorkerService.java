package service;

import constant.WorkerConstants;
import entity.SalaryHistory;
import enums.SalaryStatus;
import entity.Worker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Service class managing worker operations: add, adjust salary, and retrieve
 * history.
 */
public class WorkerService {

    private final List<Worker> workers;
    private final List<SalaryHistory> salaryHistories;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(
            "dd/MM/yyyy");

    public WorkerService(List<Worker> workers,
            List<SalaryHistory> salaryHistories) {
        this.workers = workers;
        this.salaryHistories = salaryHistories;
    }

    /**
     * Adds a new worker to the database.
     *
     * @param worker the worker to add
     * @return {@code true} if the worker was added successfully
     * @throws Exception if the worker id is null, empty, or already exists, age
     * is out of range, or salary is invalid
     */
    public boolean addWorker(Worker worker) throws Exception {
        if (worker.getId() == null || worker.getId().trim().isEmpty()) {
            throw new Exception("Worker ID cannot be null or empty.");
        }

        boolean idExists = workers.stream().anyMatch(
                w -> w.getId().equalsIgnoreCase(worker.getId()));
        if (idExists) {
            throw new Exception(
                    "Worker ID [" + worker.getId() + "] already exists.");
        }

        if (worker.getAge() < WorkerConstants.MIN_AGE || worker.getAge() > WorkerConstants.MAX_AGE) {
            throw new Exception(
                    "Age must be between " + WorkerConstants.MIN_AGE + " and " + WorkerConstants.MAX_AGE + ".");
        }
        if (worker.getSalary() <= WorkerConstants.MIN_SALARY) {
            throw new Exception(
                    "Salary must be greater than " + WorkerConstants.MIN_SALARY + ".");
        }
        return workers.add(worker);
    }

    /**
     * Adjusts the salary of a worker (increase or decrease).
     *
     * @param status UP for increase, DOWN for decrease
     * @param code   the worker's code
     * @param amount the amount to adjust
     * @return {@code true} if the salary was adjusted successfully
     * @throws Exception if the worker is not found, amount is invalid, or the
     * new salary would be invalid
     */
    public boolean changeSalary(SalaryStatus status, String code, double amount) throws Exception {
        if (amount <= WorkerConstants.MIN_ADJUSTMENT_AMOUNT) {
            throw new Exception(
                    "Amount must be greater than " + WorkerConstants.MIN_ADJUSTMENT_AMOUNT + ".");
        }

        Worker worker = findWorkerByCode(code);
        if (worker == null) {
            throw new Exception(
                    "Worker with code [" + code + "] does not exist.");
        }

        double oldSalary = worker.getSalary();
        double newSalary = (status == SalaryStatus.UP) ? oldSalary + amount : oldSalary - amount;

        if (newSalary <= WorkerConstants.MIN_SALARY) {
            throw new Exception(
                    "Action failed. New salary cannot be lower than or equal to " + WorkerConstants.MIN_SALARY + ".");
        }

        worker.setSalary(newSalary);

        String currentDate = LocalDate.now().format(DATE_FORMATTER);
        SalaryHistory history = new SalaryHistory(
                worker.getId(),
                worker.getName(),
                worker.getAge(),
                newSalary,
                status.name(),
                currentDate
        );
        return salaryHistories.add(history);
    }

    /**
     * Returns the list of all salary adjustment histories sorted by worker id.
     *
     * @return sorted list of SalaryHistory records
     */
    public List<SalaryHistory> getInformationSalary() {
        List<SalaryHistory> sorted = new ArrayList<>(salaryHistories);
        sorted.sort(Comparator.comparing(SalaryHistory::getId));
        return sorted;
    }

    /**
     * Finds a worker by their code (case-insensitive).
     *
     * @param code the worker code to search for
     * @return the Worker if found, or {@code null} otherwise
     */
    private Worker findWorkerByCode(String code) {
        return workers.stream()
                .filter(w -> w.getId().equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);
    }
}
