package org.example.backend;

public class Transaction {

    private String name;
    private double amount;
    private String date;

    public Transaction(String name,double amount,String date){
        this.name=name;
        this.amount=amount;
        this.date=date;
    }

    public String getName(){ return name; }
    public double getAmount(){ return amount; }
    public String getDate(){ return date; }

}
