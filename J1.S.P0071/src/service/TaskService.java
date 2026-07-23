package service;

import entity.Task;
import exception.TaskException;
import exception.TaskNotFoundException;

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
     * @param task task object to add
     * @return the generated task ID
     * @throws TaskException if the task object is null
     */
    public int addTask(Task task) throws TaskException {

        if (task == null) {
            throw new TaskException("Task object can not null");
        }
        tasks.add(task);
        return task.getId();
    }

    /**
     * Deletes a task by its ID after verifying it exists.
     *
     * @param taskId the ID of the task to delete
     * @return 
     *
     */
    public boolean deleteTask(int taskId) {
        return tasks.removeIf(task -> task.getId() == taskId);
    }

    /**
     * Returns all tasks in insertion order.
     *
     * @return list of all tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

}
