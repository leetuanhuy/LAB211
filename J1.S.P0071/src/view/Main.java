package view;

import constants.TaskConstants;
import controller.TaskController;
import entity.Task;
import java.util.ArrayList;
import java.util.List;
import service.TaskService;
import utils.Validation;

/**
 * Entry point of the Task Management Program (CCRM). Displays the main menu and
 * handles user navigation through the 4 program options.
 */
public class Main {

    public static int displayMenu() {
        System.out.println("\n========== Task Program ==========");
        System.out.println("1. Add Task");
        System.out.println("2. Delete Task");
        System.out.println("3. Display Task");
        System.out.println("4. Exit");
        System.out.println("==================================");

        return Validation.getInt("Enter your choice (1-4): ",
                "Invalid choice! Please enter 1-4.",
                "Invalid input! Please enter a number.",
                1, 4);
    }

    public static void main(String[] args) {
        List<Task> list = new ArrayList<>();
        TaskService service = new TaskService(list);
        TaskController controller = new TaskController(service);

        System.out.println("========================================");
        System.out.println("   Task Management Program (CCRM)");
        System.out.println("========================================");

        while (true) {
            try {
                int choice = displayMenu();

                switch (choice) {
                    case 1 ->
                        addTask(controller);
                    case 2 ->
                        deleteTask(controller);
                    case 3 ->
                        displayAll(controller);
                    case 4 -> {
                        System.out.println("\nThank you! Goodbye!");
                        return;
                    }
                    default ->
                        System.out.println("Invalid option!");
                }
            } catch (NullPointerException ex) {
                System.err.println("NullPointerException: " + ex.getMessage());
            } catch (Exception ex) {
                System.err.println("Error: " + ex.getMessage());
            }
        }
    }

    public static void addTask(TaskController controller) {
        try {
            System.out.println("\n---------- Add Task ----------");

            System.out.println("\n--- Task Types ---");
            System.out.println(TaskConstants.TASK_TYPE_CODE + " - Code");
            System.out.println(TaskConstants.TASK_TYPE_TEST + " - Test");
            System.out.println(TaskConstants.TASK_TYPE_DESIGN + " - Design");
            System.out.println(TaskConstants.TASK_TYPE_REVIEW + " - Review");
            System.out.println("------------------");

            int taskTypeID = Validation.getInt(
                    "Enter Task Type ID (" + TaskConstants.MIN_TASK_TYPE_ID + "-"
                    + TaskConstants.MAX_TASK_TYPE_ID + "): ",
                    "Task Type ID must be between " + TaskConstants.MIN_TASK_TYPE_ID
                    + " and " + TaskConstants.MAX_TASK_TYPE_ID + "!",
                    "Invalid input! Please enter a number.",
                    TaskConstants.MIN_TASK_TYPE_ID,
                    TaskConstants.MAX_TASK_TYPE_ID);

            String requirementName = Validation.getString(
                    "Enter Requirement Name: ",
                    "Requirement Name cannot be empty!");

            String date = Validation.getDate("Enter Date (dd-MM-yyyy): ");

            double planFrom = Validation.getDouble(
                    "Enter Plan From (" + TaskConstants.MIN_WORK_TIME + "-"
                    + TaskConstants.MAX_WORK_TIME + "): ",
                    "Time must be between " + TaskConstants.MIN_WORK_TIME
                    + " and " + TaskConstants.MAX_WORK_TIME + "!",
                    "Invalid input! Please enter a number.",
                    TaskConstants.MIN_WORK_TIME, TaskConstants.MAX_WORK_TIME);

            double planTo = Validation.getDouble(
                    "Enter Plan To (" + TaskConstants.MIN_WORK_TIME + "-"
                    + TaskConstants.MAX_WORK_TIME + "): ",
                    "Time must be between " + TaskConstants.MIN_WORK_TIME
                    + " and " + TaskConstants.MAX_WORK_TIME + "!",
                    "Invalid input! Please enter a number.",
                    TaskConstants.MIN_WORK_TIME, TaskConstants.MAX_WORK_TIME);

            String assignee = Validation.getString("Enter Assignee: ",
                    "Assignee cannot be empty!");

            String reviewer = Validation.getString("Enter Reviewer: ",
                    "Reviewer cannot be empty!");

            int taskId = controller.addTask(requirementName, assignee, reviewer,
                    taskTypeID, date,
                    planFrom, planTo);

            System.out.println("\nSuccess! Task added with ID: " + taskId);

        } catch (NumberFormatException ex) {
            System.err.println("NumberFormatException: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    public static void deleteTask(TaskController controller) {

        try {
            System.out.println("\n---------- Delete Task ----------");

            int taskId = Validation.getInt("Enter Task ID to delete: ",
                    "Invalid ID! Please enter a valid number.",
                    "Invalid input! Please enter a number.",
                    1, Integer.MAX_VALUE);

            controller.deleteTask(taskId);
            System.out.println(
                    "\nSuccess! Task ID " + taskId + " has been deleted.");

        } catch (NumberFormatException ex) {
            System.err.println("NumberFormatException: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    public static void displayAll(TaskController controller) {
        System.out.println("\n---------- All Tasks ----------");

        List<Task> tasks = controller.getAll();

        if (tasks.isEmpty()) {
            System.out.println("No tasks found!");
        } else {
            System.out.println(String.format(
                    "%-5s %-20s %-10s %-12s %-10s %-10s %-15s %-15s",
                    "ID", "Name", "Type", "Date", "From", "To", "Assignee",
                    "Reviewer"));
            System.out.println(
                    "--------------------------------------------------------------------------");

            for (Task task : tasks) {
                System.out.println(String.format(
                        "%-5d %-20s %-10s %-12s %-10.1f %-10.1f %-15s %-15s",
                        task.getId(),
                        task.getRequirementName(),
                        task.getTaskTypeName(),
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
