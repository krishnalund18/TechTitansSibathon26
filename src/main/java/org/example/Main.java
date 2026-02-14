package org.example;

import org.example.StorageManager;
import org.example.UserFinancialData;
import org.example.SimulationEngine;

import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Initialize storage
        StorageManager.initialize();

        while (true) {

            Menu.showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {

                case 1:
                    InputHandler.addTransaction(scanner);
                    break;

                case 2:
                    InputHandler.addSchedule(scanner);
                    break;

                case 3:
                    runForecast(scanner);
                    break;

                case 4:
                    System.out.println("Exiting application...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void runForecast(Scanner scanner) {

        UserFinancialData data = StorageManager.getData();

        System.out.print("Enter number of days to forecast: ");
        int days = scanner.nextInt();
        scanner.nextLine(); // consume newline

        SimulationEngine engine = new SimulationEngine();

        // Make sure to pass schedules; if UserFinancialData has getSchedules()
        Map<LocalDate, Double> forecast =
                engine.simulateFutureBalances(
                        data,       // user account / data
                        data.getSchedules(), // list of schedules
                        days
                );

        UI.displaySimulation(forecast);

        // Optional: show alerts
        System.out.print("Enter safety buffer amount: ");
        double buffer = scanner.nextDouble();
        scanner.nextLine();

        UI.displayAlerts(
                engine.generateLiquidityAlerts(forecast, buffer)
        );
    }


}