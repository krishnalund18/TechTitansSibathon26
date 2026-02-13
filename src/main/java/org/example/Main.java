package org.example;

import org.example.backend.*;
import org.example.person3.FileHandler;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        UserFinancialData data = new UserFinancialData();

        data.income = 50000;
        data.transactions = new ArrayList<>();

        data.transactions.add(
                new Transaction("Rent",-15000,"2026-03-01")
        );

        FileHandler.save(data);

        UserFinancialData loaded =
                FileHandler.load(UserFinancialData.class);

        System.out.println(loaded.income);

    }
}