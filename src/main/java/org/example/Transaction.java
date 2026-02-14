package org.example;

import java.time.LocalDate;
import java.util.Objects;

public class Transaction {
    private LocalDate date;
    private String type;
    private String category; // or description
    private double amount;

    public Transaction(LocalDate date, String type, String category, double amount) {
        if (amount < 0) throw new IllegalArgumentException("Amount cannot be negative");
        this.date = date;
        this.type = type.toUpperCase();
        this.category = category;
        this.amount = amount;
    }

    public LocalDate getDate() { return date; }
    public String getType() { return type; }
    public String getCategory() { return category; }
    public double getAmount() { return amount; }

    public void setDate(LocalDate date) { this.date = date; }
    public void setType(String type) { this.type = type.toUpperCase(); }
    public void setCategory(String category) { this.category = category; }
    public void setAmount(double amount) {
        if(amount < 0) throw new IllegalArgumentException("Amount cannot be negative");
        this.amount = amount;
    }

    // ✅ Add this so UI can use getDescription()
    public String getDescription() {
        return category;
    }

    @Override
    public String toString() {
        return date + " | " + type + " | " + category + " | " + amount;
    }

    // Prevent duplicates
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction t = (Transaction) o;
        return Double.compare(t.amount, amount) == 0 &&
                date.equals(t.date) &&
                type.equalsIgnoreCase(t.type) &&
                category.equalsIgnoreCase(t.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, type.toUpperCase(), category.toUpperCase(), amount);
    }
}
