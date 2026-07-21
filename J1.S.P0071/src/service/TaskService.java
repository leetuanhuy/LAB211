package service;

import constants.TaskConstants;
import entity.Task;

import java.util.List;

/**
 * Business logic layer for task management. Handles validation and in-memory
 * storage of tasks.
 */
public class TaskService {

    private final List<Task> tasks;

    public TaskService(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task after validating all business rules.
     *
     * @param task
     * @return the generated task ID
     * @throws Exception if validation fails
     */
    public int addTask(Task task) throws Exception {

        if (task.getTaskTypeID() < TaskConstants.MIN_TASK_TYPE_ID
                || task.getTaskTypeID() > TaskConstants.MAX_TASK_TYPE_ID) {
            throw new Exception("Task Type ID must be between "
                    + TaskConstants.MIN_TASK_TYPE_ID
                    + " and "
                    + TaskConstants.MAX_TASK_TYPE_ID
                    + "!");
        }

        if (task.getPlanFrom() < TaskConstants.MIN_WORK_TIME
                || task.getPlanFrom() > TaskConstants.MAX_WORK_TIME
                || task.getPlanTo() < TaskConstants.MIN_WORK_TIME
                || task.getPlanTo() > TaskConstants.MAX_WORK_TIME) {
            throw new Exception("Time must be between "
                    + TaskConstants.MIN_WORK_TIME
                    + " and "
                    + TaskConstants.MAX_WORK_TIME
                    + "!");
        }

        if ((task.getPlanFrom() * 2) % 1 != 0 || (task.getPlanTo() * 2) % 1 != 0) {
            throw new Exception(
                    "Time must be in " + TaskConstants.WORK_TIME_STEP
                    + " increments (8.0, 8.5, 9.0, ...)!");
        }

        if (task.getPlanFrom() >= task.getPlanTo()) {
            throw new Exception("Plan From must be less than Plan To!");
        }

        tasks.add(task);

        return task.getId();
    }

    /**
     * Deletes a task by its ID after verifying it exists.
     *
     * @param taskId the ID of the task to delete
     * @throws Exception if the task ID does not exist
     */
    public void deleteTask(int taskId) throws Exception {
        boolean removed = tasks.removeIf(task -> task.getId() == taskId);
        if (!removed) {
            throw new Exception("Task ID " + taskId + " does not exist!");
        }
    }

    /**
     * Returns all tasks in insertion order.
     *
     * @return list of all tasks
     */
    public List<Task> getDataTasks() {
        return tasks;
    }

}
