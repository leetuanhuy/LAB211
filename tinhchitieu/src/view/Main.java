/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package view;

import constant.InputConstant;
import controller.ExpenseController;
import exception.ExpenseException;
import exception.ExpenseNotFoundException;
import exception.InvalidAmountException;
import exception.InvalidContentException;
import exception.InvalidDateException;
import java.util.ArrayList;
import java.util.List;
import model.Expense;
import service.ExpenseService;
import utils.Validtion;

/**
 *
 * @author Admin
 */
public class Main {

    public static void main(String[] args) {
        List<Expense> list = new ArrayList<>();
        ExpenseService service = new ExpenseService(list);
        ExpenseController controller = new ExpenseController(service);
        System.out.println("======Handy Expense program =====");
        System.out.println("1.Add an expense");
        System.out.println("2.Display all expense");
        System.out.println("3.Delete an expense");
        System.out.println("4.Quit");
        while (true) {
            int choice = Validtion.getInt("Enter your choice: ",
                    "Choose option 1-4",
                    "Enter integer number",
                    InputConstant.MIN_CHOICE, InputConstant.MAX_CHOICE);
            switch (choice) {
                case 1 ->
                    addExpense(controller);
                case 2 ->
                    getAllExpense(controller);
                case 3 ->
                    deleteExpense(controller);
                case 4 ->
                    System.exit(0);
            }
        }
    }

    public static void addExpense(ExpenseController controller) {
        try {
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
            if (controller.addExpense(date, amount, content)) {
                System.out.println("Expense added successfully");
            } else {
                System.out.println("Expense added failded");
            }
        } catch (InvalidAmountException e) {
            System.err.println("Error amount: " + e.getMessage());
        }catch (InvalidContentException e) {
            System.err.println("Error content: " + e.getMessage());
        }catch (InvalidDateException e) {
            System.err.println("Error date: " + e.getMessage());
        }catch (ExpenseException e) {
            System.err.println("Error : " + e.getMessage());
        }

    }

    public static void getAllExpense(ExpenseController controller) {
        List<Expense> expenses = controller.getAllExpenses();
        if (expenses.isEmpty()) {
            System.out.println("No expenses found");
            return;
        }
        System.out.println("---------Display All Expenses-----------");
        System.out.printf("%-5s %-15s %-20s %s%n", "ID", "Date", "Amount",
                "Content");
        for (Expense exp : expenses) {
            System.out.println(exp);
        }
        System.out.println("Total: " + Validtion.formatNumberr(
                controller.getTotalAmount()));
    }

    public static void deleteExpense(ExpenseController controller) {
        try {
            int id = Validtion.getInt(
                    "Enter ID: ",
                    "ID not found",
                    "ID must be an integer",
                    InputConstant.MIN_EXPENSE_ID,
                    InputConstant.MAX_EXPENSE_ID);
            controller.deleteExpense(id);
            System.out.println("Expense deleted successfully");

        } catch (ExpenseNotFoundException e) {
            System.out.println("ID Error: " + e.getMessage());
        }

    }

}
