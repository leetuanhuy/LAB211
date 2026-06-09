package controller;

import entity.Task;
import entity.TaskType;
import service.TaskService;
import utils.Validation;
import java.util.List;

/**
 * Controller layer that coordinates between the view and service layers.
 * Handles user input, displays output, and manages exceptions
 * for the task management operations.
 */
public class TaskController {
    private TaskService service;

    /**
     * Constructs a TaskController with a new TaskService.
     */
    public TaskController() {
        service = new TaskService();
    }

    /**
     * Handles the Add Task operation.
     * Prompts user for all task details, validates input,
     * and calls the service to add the task.
     * Displays success or error messages appropriately.
     */
    public void addTask() {
        try {
            System.out.println("\n---------- Add Task ----------");

            System.out.println("\n--- Task Types ---");
            for (TaskType type : service.getTaskTypes()) {
                System.out.println(type);
            }
            System.out.println("------------------");

            int taskTypeID = Validation.getInt("Enter Task Type ID (1-4): ",
                                              "Task Type ID must be between 1 and 4!",
                                              "Invalid input! Please enter a number.",
                                              1, 4);

            String requirementName = Validation.getString("Enter Requirement Name: ",
                                                         "Requirement Name cannot be empty!");

            String date = Validation.getDate("Enter Date (dd-MM-yyyy): ");

            double planFrom = Validation.getDouble("Enter Plan From (8.0-17.5): ",
                                                  "Time must be between 8.0 and 17.5!",
                                                  "Invalid input! Please enter a number.",
                                                  8.0, 17.5);

            double planTo = Validation.getDouble("Enter Plan To (8.0-17.5): ",
                                                "Time must be between 8.0 and 17.5!",
                                                "Invalid input! Please enter a number.",
                                                8.0, 17.5);

            String assignee = Validation.getString("Enter Assignee: ",
                                                  "Assignee cannot be empty!");

            String reviewer = Validation.getString("Enter Reviewer: ",
                                                  "Reviewer cannot be empty!");

            int taskId = service.addTask(requirementName, assignee, reviewer,
                                        String.valueOf(taskTypeID), date,
                                        String.valueOf(planFrom), String.valueOf(planTo));

            System.out.println("\nSuccess! Task added with ID: " + taskId);

        } catch (NumberFormatException ex) {
            System.err.println("NumberFormatException: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    /**
     * Handles the Delete Task operation.
     * Prompts user for a task ID, validates it exists,
     * and calls the service to delete the task.
     */
    public void deleteTask() {
        try {
            System.out.println("\n---------- Delete Task ----------");

            int taskId = Validation.getInt("Enter Task ID to delete: ",
                                          "Invalid ID! Please enter a valid number.",
                                          "Invalid input! Please enter a number.",
                                          1, Integer.MAX_VALUE);

            service.deleteTask(String.valueOf(taskId));
            System.out.println("\nSuccess! Task ID " + taskId + " has been deleted.");

        } catch (NumberFormatException ex) {
            System.err.println("NumberFormatException: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    /**
     * Handles the Display Tasks operation.
     * Retrieves all tasks from the service and displays them
     * in a formatted table sorted by ID in ascending order.
     */
    public void displayTasks() {
        System.out.println("\n---------- All Tasks ----------");

        List<Task> tasks = service.getDataTasks();

        if (tasks.isEmpty()) {
            System.out.println("No tasks found!");
        } else {
            System.out.println(String.format("%-5s %-20s %-10s %-12s %-10s %-10s %-15s %-15s",
                    "ID", "Name", "Type", "Date", "From", "To", "Assignee", "Reviewer"));
            System.out.println("--------------------------------------------------------------------------");

            for (Task task : tasks) {
                String taskTypeName = service.getTaskTypeName(task.getTaskTypeID());
                System.out.println(String.format("%-5d %-20s %-10s %-12s %-10.1f %-10.1f %-15s %-15s",
                        task.getId(),
                        task.getRequirementName(),
                        taskTypeName,
                        task.getDate(),
                        task.getPlanFrom(),
                        task.getPlanTo(),
                        task.getAssignee(),
                        task.getReviewer()));
            }
        }
        System.out.println("--------------------------------");
    }
}
