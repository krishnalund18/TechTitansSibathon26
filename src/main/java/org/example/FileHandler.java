package org.example;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.LocalDateAdapter;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileHandler {
    private static final String FILE_PATH = "data.json";
    private static final String BACKUP_DIR = "backups/";

    public static void save(Object data) {
        createBackup();

        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(java.time.LocalDate.class, new LocalDateAdapter())
                    .setPrettyPrinting()
                    .create();
            gson.toJson(data, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> T load(Class<T> clazz) {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(java.time.LocalDate.class, new LocalDateAdapter())
                    .create();
            return gson.fromJson(reader, clazz);
        } catch (Exception e) {
            System.out.println("No existing data found.");
            return null;
        }
    }

    private static void createBackup() {
        try {
            Files.createDirectories(Paths.get(BACKUP_DIR));

            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

            String backupPath = BACKUP_DIR + "data_backup_" + timestamp + ".json";

            File original = new File(FILE_PATH);
            if (original.exists()) {
                Files.copy(original.toPath(), Paths.get(backupPath), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Backup created: " + backupPath);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}