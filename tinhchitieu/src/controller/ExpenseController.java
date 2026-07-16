package controller;

import constant.InputConstant;
import java.util.List;
import model.Expense;
import service.ExpenseService;
import utils.Validtion;

public class ExpenseController {
    private final ExpenseService service;

    public ExpenseController(ExpenseService service) {
        this.service = service;
    }

    /**
     * Prompts user for date, amount, and content via console input,
     * then delegates to the service layer to add the expense.
     */
    public void addExpense() {
        String date = Validtion.getDate(
                "Enter Date: ",
                "Date cannot be empty",
                "Date format is wrong (dd-MMM-yyyy)",
                "Date is invalid");
        double amount = Validtion.getDouble(
                "Enter Amount: ",
                "Amount must be greater than 0",
                "Amount must be a number",
                InputConstant.MIN_AMOUNT,
                InputConstant.MAX_AMOUNT);
        String content = Validtion.getString(
                "Enter Content: ",
                "Content cannot be empty");
        if (service.add(date, amount, content)) {
            System.out.println("Expense added successfully");
        }
    }

    /**
     * Displays all expenses in a formatted table. Prints a message if the
     * list is empty, otherwise prints each expense and the total amount.
     */
    public void displayAllExpenses() {
        List<Expense> expenses = service.getAll();
        if (expenses.isEmpty()) {
            System.out.println("No expenses found");
            return;
        }
        System.out.println("---------Display All Expenses-----------");
        System.out.printf("%-5s %-15s %-20s %s%n", "ID", "Date", "Amount", "Content");
        for (Expense exp : expenses) {
            System.out.println(exp);
        }
        System.out.println("Total: " + Validtion.formatNumberr(service.getTotalAmount()));
    }

    /**
     * Prompts user for an expense ID via console input, then delegates to
     * the service layer to delete the expense. Prints success or failure.
     */
    public void deleteExpense() {
        int id = Validtion.getInt(
                "Enter ID: ",
                "ID not found",
                "ID must be an integer",
                InputConstant.MIN_EXPENSE_ID,
                InputConstant.MAX_EXPENSE_ID);
        if (service.delete(id)) {
            System.out.println("Expense deleted successfully");
        } else {
            System.out.println("Delete failed. Expense not found.");
        }
    }
}
