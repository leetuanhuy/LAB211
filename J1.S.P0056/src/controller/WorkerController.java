package controller;

import entity.SalaryHistory;
import enums.SalaryStatus;
import entity.Worker;
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
     * @throws Exception if validation fails
     */
    public boolean addWorker(String id, String name, int age, double salary,
            String workLocation) throws Exception {
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
     * @throws Exception if worker not found or amount invalid
     */
    public boolean changeSalary(SalaryStatus status, String code, double amount) throws Exception {
        return workerService.changeSalary(status, code, amount);
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
