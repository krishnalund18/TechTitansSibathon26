package org.example;
import java.time.LocalDate;

public class Transaction {
    private LocalDate date;
    private String type;       // "Income" or "Expense"
    private String category;
    private double amount;

    // Constructor
    public Transaction(LocalDate date, String type, String category, double amount) {
        this.date = date;
        this.type = type;
        this.category = category;
        this.amount = amount;
    }

    // Getters
    public LocalDate getDate() { return date; }
    public String getType() { return type; }
    public String getCategory() { return category; }
    public double getAmount() { return amount; }

    // Setters (optional)
    public void setDate(LocalDate date) { this.date = date; }
    public void setType(String type) { this.type = type; }
    public void setCategory(String category) { this.category = category; }
    public void setAmount(double amount) { this.amount = amount; }
}
