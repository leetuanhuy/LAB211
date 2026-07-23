/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import exception.ExpenseException;
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
     * @param expense the object expense
     * @return true if add success, false if false
     * @throws exception.ExpenseException
     *
     */
    public boolean add(Expense expense) throws ExpenseException {
        if (expense == null) {
            throw new ExpenseException("Expense object does not null");
        }
        return expenses.add(expense);
    }

    /**
     * Calculate total amount
     *
     * @return the sum of amount of all expense
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
     * @return true delete success
     */
    public boolean delete(int expenseId) {
        return expenses.removeIf(exp -> exp.getId() == expenseId);
    }

}
