package org.example.backend.person1;
import java.time.LocalDate;
public class Transaction {
    private LocalDate date;
    private String type;
    private String category;
    private double amount;


    public Transaction(LocalDate date, String type, String category, double amount) {

        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        this.date = date;
        this.type = type;
        this.category = category;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        this.amount = amount;
    }
    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", type='" + type + '\'' +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                '}';
    }
}
