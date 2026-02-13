package org.example.person3;

import com.google.gson.Gson;
import java.io.*;
import java.util.*;

public class FileHandler {

    private static final String FILE_PATH = "data.json";

    public static void save(Object data) {

        try(FileWriter writer = new FileWriter(FILE_PATH)) {

            Gson gson = new Gson();
            gson.toJson(data, writer);

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static <T> T load(Class<T> clazz) {

        try(FileReader reader = new FileReader(FILE_PATH)) {

            Gson gson = new Gson();
            return gson.fromJson(reader, clazz);

        } catch(Exception e){
            System.out.println("No existing data found.");
            return null;
        }
    }
}