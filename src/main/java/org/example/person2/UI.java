package org.example.person2;
package org.example;
import org.example.Transaction;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class UI {

    public static void displayAccountSummary(UserFinancialData data) {

        System.out.println("------ Account Summary ------");
        System.out.println("Current Balance: " + data.getBalance());
        System.out.println("Total Transactions: " + data.getTransactions().size());
        System.out.println("-----------------------------");
    }

    public static void displayTransactions(List<Transaction> transactions) {

        System.out.println("------ Transactions ------");

        for (Transaction t : transactions) {
            System.out.println(t.getDate() + " | " +
                    t.getType() + " | " +
                    t.getDescription() + " | " +
                    t.getAmount());
        }

        System.out.println("--------------------------");
    }

    public static void displaySimulation(Map<LocalDate, Double> forecast) {

        System.out.println("------ Forecast Result ------");

        for (Map.Entry<LocalDate, Double> entry : forecast.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        System.out.println("-----------------------------");
    }

    public static void displayAlerts(List<String> alerts) {

        System.out.println("------ Alerts ------");

        for (String alert : alerts) {
            System.out.println(alert);
        }

        System.out.println("--------------------");
    }
}