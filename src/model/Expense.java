package model.entity;

/**
 * Expense class - Represents one expense record
 * @author Admin
 */
public class Expense {
    private int id;           // Expense ID (auto increment)
    private String date;      // Date (dd-MMM-yyyy format)
    private double amount;    // Amount of money
    private String content;   // Content/description

    /**
     * Constructor
     * @param id Expense ID
     * @param date Date
     * @param amount Amount
     * @param content Content
     */
    public Expense(int id, String date, double amount, String content) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.content = content;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return String.format("%-5d %-15s %-20.2f %s", id, date, amount, content);
    }
}
