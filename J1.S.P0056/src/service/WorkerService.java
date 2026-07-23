package service;

import entity.SalaryHistory;
import enums.SalaryStatus;
import entity.Worker;
import exception.WorkerException;

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
     * @throws exception.WorkerException
     */
    public boolean addWorker(Worker worker) throws WorkerException {
        if (worker == null) {
            throw new WorkerException("Worker is null");
        }

        return workers.add(worker);
    }

    /**
     * Check a worker Id already exist
     *
     * @param id Id to add
     * @return
     */
    public boolean isExists(String id) {

        return workers.stream().anyMatch(w -> w.getId().equalsIgnoreCase(id));
    }

    /**
     * Adjusts the salary of a worker (increase or decrease).
     *
     * @param worker
     * @param status UP for increase, DOWN for decrease
     * @param amount the amount to adjust
     * @return {@code true} if the salary was adjusted successfully
     * @throws WorkerException if the worker object fails
     */
    public boolean changeSalary(Worker worker, SalaryStatus status,
            double amount) throws WorkerException {

        if (worker == null) {
            throw new WorkerException("Worker can not null");
        }
        double oldSalary = worker.getSalary();
        double newSalary = (status == SalaryStatus.UP) ? oldSalary + amount : oldSalary - amount;

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
    public Worker findWorkerByCode(String code) {
        return workers.stream()
                .filter(w -> w.getId().equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);
    }
}
