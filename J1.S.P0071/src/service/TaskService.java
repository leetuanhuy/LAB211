package service;

import constants.TaskConstants;
import entity.Task;
import java.util.ArrayList;
import java.util.List;

/**
 * Business logic layer for task management. Handles validation and in-memory
 * storage of tasks.
 */
public class TaskService {

    private List<Task> tasks;
    private int nextId = TaskConstants.MIN_TASK_ID;

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
     * @param assignee the person assigned to the task
     * @param reviewer the person reviewing the task
     * @param taskTypeId the task type ID (1-4)
     * @param date the date in dd-MM-yyyy format
     * @param planFrom the start time (8.0-17.5, 0.5 increments)
     * @param planTo the end time (8.0-17.5, 0.5 increments)
     * @return the generated task ID
     * @throws Exception if validation fails
     */
    public int addTask(String requirementName, String assignee, String reviewer,
            int taskTypeId, String date, double planFrom, double planTo)
            throws Exception {

        if (taskTypeId < TaskConstants.MIN_TASK_TYPE_ID
                || taskTypeId > TaskConstants.MAX_TASK_TYPE_ID) {
            throw new Exception("Task Type ID must be between "
                    + TaskConstants.MIN_TASK_TYPE_ID
                    + " and "
                    + TaskConstants.MAX_TASK_TYPE_ID
                    + "!");
        }

        if (planFrom < TaskConstants.MIN_WORK_TIME
                || planFrom > TaskConstants.MAX_WORK_TIME
                || planTo < TaskConstants.MIN_WORK_TIME
                || planTo > TaskConstants.MAX_WORK_TIME) {
            throw new Exception("Time must be between "
                    + TaskConstants.MIN_WORK_TIME
                    + " and "
                    + TaskConstants.MAX_WORK_TIME
                    + "!");
        }

        if ((planFrom * 2) % 1 != 0 || (planTo * 2) % 1 != 0) {
            throw new Exception("Time must be in " + TaskConstants.WORK_TIME_STEP
                    + " increments (8.0, 8.5, 9.0, ...)!");
        }

        if (planFrom >= planTo) {
            throw new Exception("Plan From must be less than Plan To!");
        }

        int id = nextId;
        Task task = new Task(taskTypeId, requirementName,
                date, planFrom, planTo, assignee, reviewer);
        tasks.add(task);
        nextId++;
        return id;
    }

    /**
     * Deletes a task by its ID after verifying it exists.
     *
     * @param taskId the ID of the task to delete
     * @throws Exception if the task ID does not exist
     */
    public void deleteTask(int taskId) throws Exception {
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                tasks.remove(task);
                return;
            }
        }
        throw new Exception("Task ID " + taskId + " does not exist!");
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
