package controller;

import entity.Task;
import service.TaskService;
import java.util.List;

/**
 * Controller layer that coordinates between the view and service layers.
 * Handles user input, displays output, and manages exceptions for the task
 * management operations.
 */
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    /**
     * Handles the Add Task operation. Prompts user for all task details,
     *
     * @param requirementName the name of the requirement
     * @param assignee        the person assigned to the task
     * @param reviewer        the person reviewing the task
     * @param taskTypeId      the task type ID (1-4)
     * @param date            the date in dd-MM-yyyy format
     * @param planFrom        the start time (8.0-17.5, 0.5 increments)
     * @param planTo          the end time (8.0-17.5, 0.5 increments) *
     *                        validates input, and calls the service to add the
     *                        task. Displays success or error messages
     *                        appropriately.
     * @return generate task ID
     * @throws Exception if validation fails
     */
    public int addTask(String requirementName, String assignee, String reviewer,
            int taskTypeId, String date, double planFrom, double planTo)
            throws Exception {
        Task task = new Task(taskTypeId, requirementName, date, planFrom, planTo,
                assignee, reviewer);
        return service.addTask(task);

    }

    /**
     *Delete a task by Id via service
     *
     * @param taskId the Id of tasks to delete
     * @throws java.lang.Exception
     */
    public void deleteTask(int taskId) throws Exception {
        service.deleteTask(taskId);
    }

    /**
     * Get all tasks from service
     * @return list of task
     */
    public List<Task> getAll() {
        return service.getDataTasks();
    }
}
