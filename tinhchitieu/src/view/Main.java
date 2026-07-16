/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package view;

import constant.InputConstant;
import controller.ExpenseController;
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
                    controller.addExpense();
                case 2 ->
                    controller.displayAllExpenses();
                case 3 ->
                    controller.deleteExpense();
                case 4 ->
                    System.exit(0);
            }
        }
    }

}
