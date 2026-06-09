package repository;

import entity.Task;
import entity.TaskType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Data access layer for managing tasks and task types.
 * Provides CRUD operations on task data and maintains
 * the fixed list of task types (Code, Test, Design, Review).
 */
public class TaskRepository {
    private List<Task> tasks;
    private List<TaskType> taskTypes;
    private int nextTaskId;

    /**
     * Constructs a TaskRepository and initializes the task lists
     * with the 4 predefined task types.
     */
    public TaskRepository() {
        tasks = new ArrayList<>();
        taskTypes = new ArrayList<>();
        nextTaskId = 1;
        initializeTaskTypes();
    }

    /**
     * Initializes the fixed task types: 1=Code, 2=Test, 3=Design, 4=Review.
     */
    private void initializeTaskTypes() {
        taskTypes.add(new TaskType(1, "Code"));
        taskTypes.add(new TaskType(2, "Test"));
        taskTypes.add(new TaskType(3, "Design"));
        taskTypes.add(new TaskType(4, "Review"));
    }

    /**
     * Returns the list of all task types.
     *
     * @return list of TaskType objects
     */
    public List<TaskType> getTaskTypes() {
        return taskTypes;
    }

    /**
     * Checks whether a task with the given ID exists in the repository.
     *
     * @param taskId the task ID to check
     * @return true if the task exists, false otherwise
     */
    public boolean isTaskIdExists(int taskId) {
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a new task to the repository.
     *
     * @param task the Task object to add
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task by its ID from the repository.
     *
     * @param taskId the ID of the task to delete
     */
    public void deleteTask(int taskId) {
        tasks.removeIf(task -> task.getId() == taskId);
    }

    /**
     * Returns all tasks sorted by ID in ascending order.
     *
     * @return a sorted list of all tasks
     */
    public List<Task> getDataTasks() {
        Collections.sort(tasks, Comparator.comparingInt(Task::getId));
        return tasks;
    }

    /**
     * Returns the name of a task type given its ID.
     *
     * @param taskTypeID the task type ID (1-4)
     * @return the corresponding task type name, or "Unknown" if not found
     */
    public String getTaskTypeName(int taskTypeID) {
        for (TaskType type : taskTypes) {
            if (type.getId() == taskTypeID) {
                return type.getName();
            }
        }
        return "Unknown";
    }

    /**
     * Generates and returns the next available task ID.
     * The ID auto-increments starting from 1.
     *
     * @return the next task ID
     */
    public int getNextTaskId() {
        return nextTaskId++;
    }
}
