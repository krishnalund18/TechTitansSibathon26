package org.example;

import java.util.List;

public class StorageManager {

    private static UserFinancialData currentData;

    public static void initialize() {
        currentData = FileHandler.load(UserFinancialData.class);
        if (currentData == null) {
            currentData = new UserFinancialData();
        }
    }

    public static UserFinancialData getData() {
        return currentData;
    }

    public static void save() {
        FileHandler.save(currentData);
    }

    public static void autoSave() {
        new Thread(() -> {
            FileHandler.save(currentData);
            System.out.println("Auto-saved data...");
        }).start();
    }

    // Add transaction to UserFinancialData
    public static void addTransaction(Transaction transaction) {
        currentData.addTransaction(transaction);
        if (transaction.getType().equalsIgnoreCase("INCOME")) {
            currentData.setBalance(currentData.getBalance() + transaction.getAmount());
        } else {
            currentData.setBalance(currentData.getBalance() - transaction.getAmount());
        }
        autoSave();
    }

    // Add schedule to UserFinancialData
    public static void addSchedule(Schedule schedule) {
        currentData.addSchedule(schedule);
        autoSave();
        System.out.println("Schedule stored in user data.");
    }
}