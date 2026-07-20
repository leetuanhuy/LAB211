package controller;


import java.util.List;
import model.Expense;
import service.ExpenseService;

public class ExpenseController {

    private final ExpenseService service;

    public ExpenseController(ExpenseService service) {
        this.service = service;
    }

    /**
     * Add expense to list
     *
     * @param date    date of expense
     * @param amount  amount of money
     * @param content content/description
     * @return
     *
     */
    public boolean addExpense(String date, double amount, String content) {
        Expense expense = new Expense(date, amount, content);
        return service.add(expense);
    }

    /**
     * Displays all expenses in a formatted table. Prints a message if the list
     * is empty, otherwise prints each expense and the total amount.
     *
     * @return
     */
    public List<Expense> displayAllExpenses() {
        return service.getAll();
    }

    /**
     *
     * @return
     */
    public double getTotalAmount() {
        return service.getTotalAmount();
    }

    /**
     * 
     * Service layer to delete the expense. 
     * @param id
     * @return 
     */
    public boolean deleteExpense(int id) {
        return service.delete(id);
    }
}
