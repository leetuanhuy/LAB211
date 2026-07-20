/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import constant.InputConstant;

/**
 *
 * @author Admin
 */
public class Expense {

    private static int id = InputConstant.MIN_EXPENSE_ID;
    private String date;
    private double amount;
    private String content;

    public Expense(String date, double amount, String content) {
        Expense.id = id++;
        this.date = date;
        this.amount = amount;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        Expense.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {      
        return String.format("%-5d %-15s %-20s %s", id, date, amount, content);
    }
}
