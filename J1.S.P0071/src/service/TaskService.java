package service;

import entity.Task;
import entity.TaskType;
import repository.TaskRepository;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Business logic layer for task management.
 * Handles validation of task data and coordinates between
 * the controller and repository layers.
 */
public class TaskService {
    private TaskRepository repo;

    /**
     * Constructs a TaskService with a new TaskRepository.
     */
    public TaskService() {
        repo = new TaskRepository();
    }

    /**
     * Adds a new task after validating all input data.
     * Validates task type ID (1-4), date format (dd-MM-yyyy),
     * time range (8.0-17.5), 0.5 hour increments, and planFrom must be less than planTo.
     *
     * @param requirementName the name of the requirement
     * @param assignee        the person assigned to the task
     * @param reviewer        the person reviewing the task
     * @param taskTypeID      the task type ID as a string (will be parsed to int)
     * @param date            the date in dd-MM-yyyy format
     * @param planFrom        the start time as a string (will be parsed to double)
     * @param planTo          the end time as a string (will be parsed to double)
     * @return the generated task ID
     * @throws NumberFormatException if taskTypeID, planFrom, or planTo cannot be parsed as numbers
     * @throws Exception             if validation fails for any business rule
     */
    public int addTask(String requirementName, String assignee, String reviewer,
                       String taskTypeID, String date, String planFrom, String planTo) throws Exception {

        Integer typeId = Integer.valueOf(taskTypeID);
        if (typeId < 1 || typeId > 4) {
            throw new Exception("Task Type ID must be between 1 and 4!");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
        } catch (Exception e) {
            throw new Exception("Invalid date format! Must be dd-MM-yyyy!");
        }

        Double from = Double.valueOf(planFrom);
        Double to = Double.valueOf(planTo);

        if (from < 8.0 || from > 17.5 || to < 8.0 || to > 17.5) {
            throw new Exception("Time must be between 8.0 and 17.5!");
        }

        if ((from * 10) % 5 != 0 || (to * 10) % 5 != 0) {
            throw new Exception("Time must be in 0.5 increments (8.0, 8.5, 9.0, ...)!");
        }

        if (from >= to) {
            throw new Exception("Plan From must be less than Plan To!");
        }

        int id = repo.getNextTaskId();
        Task task = new Task(id, typeId, requirementName, date, from, to, assignee, reviewer);
        repo.addTask(task);
        return id;
    }

    /**
     * Deletes a task by its ID after verifying the task exists.
     *
     * @param id the task ID as a string (will be parsed to int)
     * @throws NumberFormatException if the ID cannot be parsed as a number
     * @throws Exception             if the task ID does not exist
     */
    public void deleteTask(String id) throws Exception {
        Integer taskId = Integer.valueOf(id);

        if (!repo.isTaskIdExists(taskId)) {
            throw new Exception("Task ID " + taskId + " does not exist!");
        }

        repo.deleteTask(taskId);
    }

    /**
     * Returns all tasks sorted by ID in ascending order.
     *
     * @return a sorted list of all tasks
     */
    public List<Task> getDataTasks() {
        return repo.getDataTasks();
    }

    /**
     * Returns the list of all task types.
     *
     * @return list of TaskType objects
     */
    public List<TaskType> getTaskTypes() {
        return repo.getTaskTypes();
    }

    /**
     * Returns the name of a task type given its ID.
     *
     * @param taskTypeID the task type ID (1-4)
     * @return the task type name, or "Unknown" if not found
     */
    public String getTaskTypeName(int taskTypeID) {
        return repo.getTaskTypeName(taskTypeID);
    }
}
