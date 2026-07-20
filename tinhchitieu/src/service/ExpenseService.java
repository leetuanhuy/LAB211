/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import model.Expense;

/**
 *
 * @author Admin
 */
public class ExpenseService {

    private final List<Expense> expenses;

    public ExpenseService(List<Expense> expenses) {
        this.expenses = expenses;
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
     * Add expense to list. ID auto increment, not reuse after delete.
     *
     * @param expense
     * @return true if add success, false if false
     *
     */
    public boolean add(Expense expense) {
        return expenses.add(expense);
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

}
