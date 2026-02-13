package org.example;

import org.example.backend.person1.Transaction;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        Transaction salary = new Transaction(
                LocalDate.of(2026, 2, 13),
                "Income",
                "Salary",
                50000
        );

        System.out.println(salary);
    }
}
