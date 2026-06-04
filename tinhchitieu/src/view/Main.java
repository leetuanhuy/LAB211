/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package view;

import java.util.List;
import model.Expense;
import service.ExpenseService;
import utils.Validtion;

/**
 *
 * @author Admin
 */
public class Main {

    private static ExpenseService service = new ExpenseService();

    public static void main(String[] args) {
        System.out.println("======Handy Expense program =====");
        System.out.println("1.Add an expense");
        System.out.println("2.Display all expense");
        System.out.println("3.Delete an expense");
        System.out.println("4.Quit");
        while (true) {
            int choice = Validtion.getInt("Enter your choice: ",
                    "Choose option 1-4",
                    "Enter integer number",
                    1, 4);
            switch (choice) {
                case 1 ->
                    add();
                case 2 ->
                    displayAll();
                case 3 ->
                    delete();
                case 4 ->
                    System.exit(0);
            }
        }
    }

    public static void add() {
        System.out.println("-----Add an expense-----");
        String date = Validtion.getDate("Enter date: ",
                "Do not empty",
                "Enter correct format(example: 11-Apr-2004)",
                "Incorrect date format");

        double amount = Validtion.getDouble("Enter Amount: ",
                "Amount must be >= 0", "Input must be number",
                0, Double.MAX_VALUE);

        String content = Validtion.getString("Enter content: ",
                "Do not empty");
        if (service.add(date, amount, content)) {
            System.out.println("Success!");
        } else {

            System.out.println("Add faild");
        }

    }

    public static void displayAll() {
        System.out.println("-----Display all exnpense-------");
        List<Expense> list = service.getAll();
        if (list.isEmpty()) {
            System.out.println("No expense");
        }
        for (Expense exp : list) {
            System.out.println(exp);
        }
        String total = Validtion.formatNumberr(service.getTotalAmount());
        System.out.printf("%-5s %-15s %-20s\n", "", "Total:", total);
    }

    public static void delete() {
        System.out.println("--- Delete an expense ---");
        if (service.getExpenseCount() == 0) {
            System.out.println("No expense");
            return;
        }
        int enxpeseId = Validtion.getInt("Enter ID: ",
                "Invalid ID",
                "Enter integer number",
                1, Integer.MAX_VALUE);
        if (service.delete(enxpeseId)) {
            System.out.println("delete success");
        } else {
            System.out.println("delete faild");
        }
    }

}
