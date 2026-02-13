package org.example;

import org.example.person3.StorageManager;
import org.example.backend.*;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        StorageManager.initialize();

        UserFinancialData data =
                StorageManager.getData();

        if(data.transactions == null){

            data.transactions = new ArrayList<>();

        }

        data.income = 50000;

        data.transactions.add(
                new Transaction("Rent",-15000,"2026-03-01")
        );

        StorageManager.save();

    }
}