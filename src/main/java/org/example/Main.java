package org.example;

import org.example.backend.person1.Transaction;
import org.example.backend.person1.Schedule;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Transaction rent = new Transaction(
                LocalDate.of(2026, 2, 1),
                "Expense",
                "Rent",
                15000
        );

        Transaction salary = new Transaction(
                LocalDate.of(2026, 2, 13),
                "Income",
                "Salary",
                50000
        );

        Transaction subscription = new Transaction(
                LocalDate.of(2026, 2, 15),
                "Expense",
                "Netflix",
                1500
        );

        System.out.println("---- Transactions ----");
        System.out.println(rent);
        System.out.println(salary);
        System.out.println(subscription);

        Schedule monthlyRent = new Schedule(rent, "MONTHLY", rent.getDate());
        Schedule monthlySalary = new Schedule(salary, "MONTHLY", salary.getDate());
        Schedule monthlyNetflix = new Schedule(subscription, "MONTHLY", subscription.getDate());


        System.out.println("\n---- Initial Schedules ----");
        System.out.println(monthlyRent);
        System.out.println(monthlySalary);
        System.out.println(monthlyNetflix);

        monthlyRent.calculateNextDate();
        monthlySalary.calculateNextDate();
        monthlyNetflix.calculateNextDate();


        System.out.println("\n---- Schedules After Next Month ----");
        System.out.println(monthlyRent);
        System.out.println(monthlySalary);
        System.out.println(monthlyNetflix);
    }
}

