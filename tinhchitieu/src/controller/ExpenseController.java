package controller;

import constant.InputConstant;
import exception.ExpenseException;
import exception.ExpenseNotFoundException;
import exception.InvalidAmountException;
import exception.InvalidContentException;
import exception.InvalidDateException;
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
     * @return if expense add successfully
     * @throws ExpenseException        if Expense object null
     * @throws InvalidDateException    if date null or empty
     * @throws InvalidAmountException  if amount less than 0
     * @throws InvalidContentException if content null or empty
     *
     */
    public boolean addExpense(String date, double amount, String content) throws
            ExpenseException,
            InvalidDateException, InvalidAmountException,
            InvalidContentException {
        Expense expense = new Expense(date, amount, content);
        if (date == null || date.trim().isEmpty()) {
            throw new InvalidDateException("Date be can not null or empty");
        }
        if (amount <= InputConstant.MIN_AMOUNT) {
            throw new InvalidAmountException("Amount must be greather than )0");
        }
        if (content == null || content.trim().isEmpty()) {
            throw new InvalidContentException("content be can not null or empty");
        }

        return service.add(expense);
    }

    /**
     * Get all expense via service
     *
     * @return list of all expense
     */
    public List<Expense> getAllExpenses() {
        return service.getAll();
    }

    /**
     *Total amount of all recorded expense
     * 
     * @return the sum of amount of all expense
     */
    public double getTotalAmount() {
        return service.getTotalAmount();
    }

    /**
     *
     * Service layer to delete the expense.
     *
     * @param id the id of expense to delete
     * @throws ExpenseNotFoundException if no expense not found with id exist
     */
    public void deleteExpense(int id) throws ExpenseNotFoundException {
        boolean isDelete = service.delete(id);
        if (!isDelete) {
            throw new ExpenseNotFoundException(
                    "Delete fail! Expense Id: " + id + " does not exsits");
        }

    }
}
