package org.example.person2;
import java.util.ArrayList;
import java.util.List;

public class UserAccount {
    private String name;
    private double balance;
    private List<Transaction> transactions;

    public UserAccount(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction t) {
        transactions.add(t);
        if(t.getType().equalsIgnoreCase("Income")) {
            balance += t.getAmount();
        } else {
            balance -= t.getAmount();
        }
    }

    public void removeTransaction(Transaction t) {
        if(transactions.remove(t)) {
            if(t.getType().equalsIgnoreCase("Income")) {
                balance -= t.getAmount();
            } else {
                balance += t.getAmount();
            }
        }
    }

    public List<Transaction> getTransactionsByType(String type) {
        List<Transaction> filtered = new ArrayList<>();
        for(Transaction t : transactions) {
            if(t.getType().equalsIgnoreCase(type)) {
                filtered.add(t);
            }
        }
        return filtered;
    }

    public double getTotalIncome() {
        double total = 0;
        for(Transaction t : transactions) {
            if(t.getType().equalsIgnoreCase("Income")) {
                total += t.getAmount();
            }
        }
        return total;
    }

    public double getTotalExpense() {
        double total = 0;
        for(Transaction t : transactions) {
            if(t.getType().equalsIgnoreCase("Expense")) {
                total += t.getAmount();
            }
        }
        return total;
    }

    public void printSummary() {
        System.out.println("----- Account Summary for " + name + " -----");
        System.out.println("Current Balance: " + balance);
        System.out.println("Total Income: " + getTotalIncome());
        System.out.println("Total Expenses: " + getTotalExpense());
        System.out.println("Transactions:");
        for(Transaction t : transactions) {
            System.out.println(t.getDate() + " | " + t.getType() + " | " + t.getCategory() + " | " + t.getAmount());
        }
        System.out.println("-------------------------------------------");
    }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    public List<Transaction> getTransactions() { return transactions; }
}
