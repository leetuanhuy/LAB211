package controller;

import constants.TaskConstants;
import entity.Task;
import exception.InvalidTaskTimeException;
import exception.TaskException;
import exception.TaskNotFoundException;
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
     * Adds a task via service
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
     * @throws InvalidTaskTimeException if the task type Id or working time is
     * valid
     */
    public int addTask(String requirementName, String assignee, String reviewer,
            int taskTypeId, String date, double planFrom, double planTo)
            throws InvalidTaskTimeException, TaskException {
        if (taskTypeId < TaskConstants.MIN_TASK_TYPE_ID
                || taskTypeId > TaskConstants.MAX_TASK_TYPE_ID) {
            throw new InvalidTaskTimeException("Task Type ID must be between "
                    + TaskConstants.MIN_TASK_TYPE_ID
                    + " and "
                    + TaskConstants.MAX_TASK_TYPE_ID
                    + "!");
        }

        if (planFrom < TaskConstants.MIN_WORK_TIME
                || planFrom > TaskConstants.MAX_WORK_TIME
                || planTo < TaskConstants.MIN_WORK_TIME
                || planTo > TaskConstants.MAX_WORK_TIME) {
            throw new InvalidTaskTimeException("Time must be between "
                    + TaskConstants.MIN_WORK_TIME
                    + " and "
                    + TaskConstants.MAX_WORK_TIME
                    + "!");
        }

        if ((planFrom * 2) % 1 != 0 || (planTo * 2) % 1 != 0) {
            throw new InvalidTaskTimeException(
                    "Time must be in " + TaskConstants.WORK_TIME_STEP
                    + " increments (8.0, 8.5, 9.0, ...)!");
        }

        if (planFrom >= planTo) {
            throw new InvalidTaskTimeException(
                    "Plan From must be less than Plan To!");
        }

        Task task = new Task(taskTypeId, requirementName, date, planFrom, planTo,
                assignee, reviewer);
        return service.addTask(task);

    }

    /**
     * Delete a task by Id via service
     *
     * @param taskId the Id of tasks to delete
     * @throws TaskNotFoundException if no task with the Id exists
     */
    public void deleteTask(int taskId) throws TaskNotFoundException {
        boolean isDelete = service.deleteTask(taskId);
        if (!isDelete) {
            throw new TaskNotFoundException(
                    "Delete an Task fail ! Task Id = " + taskId + " does not exisis");
        }

    }

    /**
     * Get all tasks from service
     *
     * @return list of task
     */
    public List<Task> getAll() {
        return service.getTasks();
    }
}
