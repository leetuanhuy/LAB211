/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import java.util.List;
import model.Expense;

/**
 *
 * @author Admin
 */
public class ExpenseService {

    private final ArrayList<Expense> expenses;

    public ExpenseService() {
        this.expenses = new ArrayList<>();
    }

    /**
     * Get all expenses list
     *
     * @return list of all expenses
     */
    public List<Expense> getAll() {
        return expenses;

    }

    /**
     * Helper: Calculate next ID. Formula: next ID = max ID + 1. If list
     * empty,max ID = 0 so return 1
     *
     * @return next Id
     */
    private int getNextId() {
        int maxId = 0;
        for (Expense exp : expenses) {
            if (exp.getId() > maxId) {
                maxId = exp.getId();
            }
        }
        return maxId + 1;
    }

    /**
     * Add expense to list ID auto increment: new ID = max ID + 1
     *
     * @param date date of expense
     * @param amount amount of money
     * @param content content/description
     * @return true if add success, false if false
     */
    public boolean add(String date, double amount, String content) {
        try {
            int nextId = getNextId();
            Expense expense = new Expense(nextId, date, amount, content);
            expenses.add(expense);
            return true;
        } catch (Exception e) {
            System.err.println("Add faild");
            return false;
        }

    }

    /**
     * Helper: Calculate total amount
     *
     * @return
     */
    public double getTotalAmount() {
        double total = 0;
        for (Expense exp : expenses) {
            total += exp.getAmount();
        }
        return total;
    }

    /**
     * Delete expense by id
     *
     * @param expenseId ID of expense to delete
     * @return true delete success, false if ID not found
     */
    public boolean delete(int expenseId) {
        for (Expense exp : expenses) {
            if (exp.getId() == expenseId) {
                expenses.remove(exp);
                return true;
            }
        }
        return false;
    }

    /**
     * Get number of expense
     *
     * @return size of expense list
     */
    public int getExpenseCount() {
        return expenses.size();
    }

}
