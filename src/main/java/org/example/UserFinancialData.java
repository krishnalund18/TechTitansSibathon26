package org.example;

import java.util.ArrayList;
import java.util.List;

public class UserFinancialData {

    private double balance;
    private List<Transaction> transactions;
    private List<Schedule> schedules;

    public UserFinancialData() {
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
        this.schedules = new ArrayList<>();
    }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public List<Transaction> getTransactions() { return transactions; }
    public void setTransactions(List<Transaction> transactions) { this.transactions = transactions; }

    public List<Schedule> getSchedules() { return schedules; }
    public void setSchedules(List<Schedule> schedules) { this.schedules = schedules; }

    public void addTransaction(Transaction transaction) { this.transactions.add(transaction); }
    public void addSchedule(Schedule schedule) { this.schedules.add(schedule); }

    @Override
    public String toString() {
        return "UserFinancialData{" +
                "balance=" + balance +
                ", transactions=" + transactions +
                ", schedules=" + schedules +
                '}';
    }
}