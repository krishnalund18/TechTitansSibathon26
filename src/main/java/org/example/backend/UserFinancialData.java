package org.example.backend;

import java.util.List;

public class UserFinancialData {
    public UserFinancialData(){
        income = 0;
    }
    public double income;
    public List<Transaction> transactions;
}