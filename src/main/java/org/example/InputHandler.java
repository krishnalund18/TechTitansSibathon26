package org.example;

import java.time.LocalDate;
import java.util.Scanner;
import org.example.Schedule;
import org.example.StorageManager;
import org.example.Transaction;

import java.time.LocalDate;
import java.util.Scanner;

    public class InputHandler {

        public static void addTransaction(Scanner scanner) {

            System.out.print("Enter date (YYYY-MM-DD): ");
            LocalDate date = LocalDate.parse(scanner.nextLine());

            System.out.print("Enter type (Income/Expense): ");
            String type = scanner.nextLine();

            System.out.print("Enter description: ");
            String description = scanner.nextLine();

            System.out.print("Enter amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();

            Transaction transaction = new Transaction(date, type, description, amount);
            StorageManager.addTransaction(transaction);

            System.out.println("Transaction added successfully!");
        }

        public static void addSchedule(Scanner scanner) {

            System.out.print("Enter start date (YYYY-MM-DD): ");
            LocalDate startDate = LocalDate.parse(scanner.nextLine());

            System.out.print("Enter recurrence (DAILY/WEEKLY/MONTHLY): ");
            String recurrence = scanner.nextLine();

            System.out.print("Enter description: ");
            String description = scanner.nextLine();

            System.out.print("Enter type (Income/Expense): ");
            String type = scanner.nextLine();

            System.out.print("Enter amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();

            // ✅ Correct creation using helper method
            Schedule schedule = Schedule.createSchedule(startDate, recurrence, description, type, amount);

            StorageManager.addSchedule(schedule);

            System.out.println("Schedule added successfully!");
        }
    }