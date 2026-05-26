package service;

import model.entity.Expense;
import java.util.*;

/**
 * Service class for managing expenses
 * Contains 3 main methods:
 * - addExpense(): Add new expense
 * - displayAll(): Get all expenses (return list)
 * - deleteExpense(): Delete expense
 * @author Admin
 */
public class ExpenseService {
    private List<Expense> expenses;

    /**
     * Constructor - Initialize expense list
     */
    public ExpenseService() {
        this.expenses = new ArrayList<>();
    }

    /**
     * Method 1: Add expense to list
     * ID auto increment: new ID = max ID + 1 (first ID = 1)
     * @param date Date of expense
     * @param amount Amount of money
     * @param content Content/description
     * @return true if add success, false if fail
     */
    public boolean addExpense(String date, double amount, String content) {
        try {
            int nextId = getNextId();
            Expense expense = new Expense(nextId, date, amount, content);
            expenses.add(expense);
            return true;
        } catch (Exception e) {
            System.err.println("Error adding expense: " + e.getMessage());
            return false;
        }
    }

    /**
     * Method 2: Get all expenses list (for View layer to display)
     * @return List of all expenses
     */
    public List<Expense> getAllExpenses() {
        return expenses;
    }

    /**
     * Method 3: Delete expense by ID
     * @param expenseId ID of expense to delete
     * @return true if delete success, false if ID not found
     */
    public boolean deleteExpense(int expenseId) {
        for (Expense exp : expenses) {
            if (exp.getId() == expenseId) {
                expenses.remove(exp);
                return true;
            }
        }
        return false;  // ID not found
    }

    /**
     * Helper: Calculate next ID
     * Formula: next ID = max ID + 1 (if list empty, ID = 1)
     * @return Next ID
     */
    private int getNextId() {
        if (expenses.isEmpty()) {
            return 1;
        }
        
        // Find max ID
        int maxId = 0;
        for (int i = 0; i < expenses.size(); i++) {
            Expense exp = expenses.get(i);
            if (exp.getId() > maxId) {
                maxId = exp.getId();
            }
        }
        return maxId + 1;
    }

    /**
     * Helper: Calculate total amount
     * @return Total amount of all expenses
     */
    public double getTotalAmount() {
        double total = 0;
        for (int i = 0; i < expenses.size(); i++) {
            Expense exp = expenses.get(i);
            total += exp.getAmount();
        }
        return total;
    }

    /**
     * Helper: Get number of expenses
     * @return Size of expense list
     */
    public int getExpenseCount() {
        return expenses.size();
    }

    /**
     * Helper: Get expense by ID
     * @param id ID of expense
     * @return Expense object or null
     */
    public Expense getExpenseById(int id) {
        for (Expense exp : expenses) {
            if (exp.getId() == id) {
                return exp;
            }
        }
        return null;
    }
}
