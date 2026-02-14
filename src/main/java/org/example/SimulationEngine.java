package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SimulationEngine {

    // Simulate future balances for given days
    public Map<LocalDate, Double> simulateFutureBalances(UserFinancialData data, List<Schedule> schedules, int days) {

        List<Transaction> allTransactions = new ArrayList<>(data.getTransactions());
        allTransactions.addAll(Schedule.generateTransactionsFromSchedules(schedules, days));

        Map<LocalDate, Double> futureBalances = new LinkedHashMap<>();
        double balance = data.getBalance();
        LocalDate today = LocalDate.now();

        for (int i = 0; i < days; i++) {
            LocalDate current = today.plusDays(i);
            for (Transaction t : allTransactions) {
                if (t.getDate().equals(current)) {
                    if (t.getType().equalsIgnoreCase("INCOME")) balance += t.getAmount();
                    else balance -= t.getAmount();
                }
            }
            futureBalances.put(current, balance);
        }

        return futureBalances;
    }

    // Generate alerts for balance below safety buffer
    public List<String> generateLiquidityAlerts(Map<LocalDate, Double> futureBalances, double safetyBuffer) {
        List<String> alerts = new ArrayList<>();
        for (Map.Entry<LocalDate, Double> entry : futureBalances.entrySet()) {
            if (entry.getValue() < safetyBuffer) {
                alerts.add("⚠ Warning! Balance below buffer on " + entry.getKey() + ": " + entry.getValue());
            }
        }
        return alerts;
    }
}