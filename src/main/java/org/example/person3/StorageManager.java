package org.example.person3;

import org.example.backend.UserFinancialData;

public class StorageManager {

    private static UserFinancialData currentData;

    public static void initialize(){

        currentData =
                FileHandler.load(UserFinancialData.class);

        if(currentData == null){

            currentData = new UserFinancialData();

        }
    }

    public static UserFinancialData getData(){
        return currentData;
    }

    // normal save
    public static void save(){
        FileHandler.save(currentData);
    }

    // ⭐ AUTO SAVE (NEW UPGRADE)
    public static void autoSave(){

        new Thread(() -> {
            FileHandler.save(currentData);
            System.out.println("Auto-saved data...");
        }).start();

    }
}