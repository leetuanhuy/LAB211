package service;

import entity.Task;
import java.util.ArrayList;
import java.util.List;

/**
 * Business logic layer for task management.
 * Handles validation and in-memory storage of tasks.
 */
public class TaskService {
    private List<Task> tasks;

    /**
     * Constructs a TaskService with an empty task list.
     */
    public TaskService() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a new task after validating all business rules.
     *
     * @param requirementName the name of the requirement
     * @param assignee        the person assigned to the task
     * @param reviewer        the person reviewing the task
     * @param taskTypeId      the task type ID (1-4)
     * @param date            the date in dd-MM-yyyy format
     * @param planFrom        the start time (8.0-17.5, 0.5 increments)
     * @param planTo          the end time (8.0-17.5, 0.5 increments)
     * @return the generated task ID
     * @throws Exception if validation fails
     */
    public int addTask(String requirementName, String assignee, String reviewer,
                        int taskTypeId, String date, double planFrom, double planTo) throws Exception {

        if (taskTypeId < 1 || taskTypeId > 4) {
            throw new Exception("Task Type ID must be between 1 and 4!");
        }

        if (planFrom < 8.0 || planFrom > 17.5 || planTo < 8.0 || planTo > 17.5) {
            throw new Exception("Time must be between 8.0 and 17.5!");
        }

        if ((planFrom * 10) % 5 != 0 || (planTo * 10) % 5 != 0) {
            throw new Exception("Time must be in 0.5 increments (8.0, 8.5, 9.0, ...)!");
        }

        if (planFrom >= planTo) {
            throw new Exception("Plan From must be less than Plan To!");
        }

        int id = getNextId();
        Task task = new Task(id, taskTypeId, requirementName, date, planFrom, planTo, assignee, reviewer);
        tasks.add(task);
        return id;
    }

    /**
     * Deletes a task by its ID after verifying it exists.
     *
     * @param taskId the ID of the task to delete
     * @throws Exception if the task ID does not exist
     */
    public void deleteTask(int taskId) throws Exception {
        boolean exists = tasks.stream().anyMatch(t -> t.getId() == taskId);
        if (!exists) {
            throw new Exception("Task ID " + taskId + " does not exist!");
        }
        tasks.removeIf(task -> task.getId() == taskId);
    }

    /**
     * Returns all tasks in insertion order.
     *
     * @return list of all tasks
     */
    public List<Task> getDataTasks() {
        return tasks;
    }

    /**
     * Generates the next available task ID by finding the max existing ID.
     *
     * @return next ID = max existing ID + 1
     */
    private int getNextId() {
        int maxId = 0;
        for (Task task : tasks) {
            if (task.getId() > maxId) {
                maxId = task.getId();
            }
        }
        return maxId + 1;
    }
}
