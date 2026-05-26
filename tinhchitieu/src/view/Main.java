/*
 * HANDY EXPENSE - Expense Management Program
 */
package view;

import model.entity.Expense;
import service.ExpenseService;
import utils.Validation;
import java.util.List;

/**
 * Main class - User interface
 * Display menu and handle user choices
 * THIS CLASS: Only for displaying UI (View Layer)
 * @author Admin
 */
public class Main {
    private static ExpenseService service;

    public static void main(String[] args) {
        service = new ExpenseService();
        
        runProgram();
    }

  
    /**
     * Main program loop - Display menu and handle user choice
     */
    private static void runProgram() {
        int choice;

        while (true) {
            displayMenu();
            choice = Validation.getInt(
                    "Enter your choice (1-4): ",
                    "Invalid choice! Please enter 1-4",
                    "Invalid input! Please enter a number",
                    1, 4
            );

            switch (choice) {
                case 1:
                    addExpenseUI();
                    break;
                case 2:
                    displayAllExpensesUI();
                    break;
                case 3:
                    deleteExpenseUI();
                    break;
                case 4:
                    exitProgram();
                    return;
            }
        }
    }

    /**
     * Display main menu
     */
    private static void displayMenu() {
        System.out.println("MENU");
        System.out.println("1. Add an expense");
        System.out.println("2. Display all expenses");
        System.out.println("3. Remove an expense");
        System.out.println("4. Exit");
    }

    /**
     * Option 1: Add an expense
     * Input: Date, Amount, Content
     * ID auto increment
     */
    private static void addExpenseUI() {
        System.out.println("\n--- ADD AN EXPENSE ---");

        // Input date with validation
        String date = Validation.getDate("Enter date (dd-MMM-yyyy, example: 11-Apr-2009): ");

        // Input amount
        double amount = Validation.getDouble(
                "Enter amount: ",
                "Amount must be greater than 0",
                "Invalid input! Please enter a number",
                0, Double.MAX_VALUE
        );

        // Input content with validation
        String content = Validation.getString(
                "Enter content: ",
                "Error: Content cannot be empty!"
        );

        // Add expense
        if (service.addExpense(date, amount, content)) {
            System.out.println("Success! Expense added (Auto ID: " + service.getExpenseCount() + ")");
        } else {
            System.out.println("Failed to add expense!");
        }
    }

    /**
     * Option 2: Display all expenses
     * This is View Layer - responsibility to format and display
     */
    private static void displayAllExpensesUI() {
        System.out.println("\n--- DISPLAY ALL EXPENSES ---");

        // Get data from Service
        List<Expense> list = service.getAllExpenses();

        if (list.isEmpty()) {
            System.out.println("No expenses!");
            return;
        }
        // Display each expense
        for (int i = 0; i < list.size(); i++) {
            Expense exp = list.get(i);
            System.out.printf("%-5d %-15s %-20.2f %s\n", 
                    exp.getId(), exp.getDate(), exp.getAmount(), exp.getContent());
        }

        
        System.out.printf("%-5s %-15s %-20.2f\n", "", "Total:", service.getTotalAmount());
    }

    /**
     * Option 3: Delete an expense
     * Input: Expense ID
     * If ID not exist: "Delete an expense fail"
     * If delete success: "Delete an expense successful"
     */
    private static void deleteExpenseUI() {
        System.out.println("\n--- REMOVE AN EXPENSE ---");

        // Check if have expenses
        if (service.getExpenseCount() == 0) {
            System.out.println("No expenses to delete!");
            return;
        }

        // Display all first
        displayAllExpensesUI();

        // Input ID to delete
        int expenseId = Validation.getInt(
                "Enter expense ID to delete: ",
                "Invalid ID!",
                "Invalid input! Please enter a number",
                1, Integer.MAX_VALUE
        );

        // Delete expense
        if (service.deleteExpense(expenseId)) {
            System.out.println("Delete an expense successful");
        } else {
            System.out.println("Delete an expense fail");
        }
    }

    /**
     * Option 4: Exit program
     */
    private static void exitProgram() {
        System.out.println("\nThank you for using HANDY EXPENSE!");
        System.out.println("Goodbye!");
        System.exit(0);
    }

  
}
