package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Schedule {

    private Transaction transaction;
    private String recurrenceType;
    private LocalDate nextDate;

    public Schedule(Transaction transaction, String recurrenceType, LocalDate nextDate) {
        this.transaction = transaction;
        this.recurrenceType = recurrenceType.toUpperCase();
        this.nextDate = nextDate;
    }

    public Transaction getTransaction() { return transaction; }
    public String getRecurrenceType() { return recurrenceType; }
    public LocalDate getNextDate() { return nextDate; }
    public void setNextDate(LocalDate nextDate) { this.nextDate = nextDate; }

    public void calculateNextDate() {
        switch (recurrenceType) {
            case "DAILY" -> nextDate = nextDate.plusDays(1);
            case "WEEKLY" -> nextDate = nextDate.plusWeeks(1);
            case "MONTHLY" -> nextDate = nextDate.plusMonths(1);
            default -> throw new IllegalArgumentException("Invalid recurrence type: " + recurrenceType);
        }
    }

    // Replace this method inside Schedule.java
    public static List<Transaction> generateTransactionsFromSchedules(List<Schedule> schedules, int daysAhead) {
        List<Transaction> generated = new ArrayList<>();
        LocalDate limitDate = LocalDate.now().plusDays(daysAhead);

        for (Schedule s : schedules) {
            // Use a temporary variable so we don't change the actual Schedule object
            LocalDate tempNextDate = s.getNextDate();

            while (!tempNextDate.isAfter(limitDate)) {
                Transaction t = new Transaction(
                        tempNextDate,
                        s.getTransaction().getType(),
                        s.getTransaction().getCategory(),
                        s.getTransaction().getAmount()
                );
                generated.add(t);

                // Calculate the next occurrence for the simulation ONLY
                tempNextDate = switch (s.getRecurrenceType()) {
                    case "DAILY" -> tempNextDate.plusDays(1);
                    case "WEEKLY" -> tempNextDate.plusWeeks(1);
                    case "MONTHLY" -> tempNextDate.plusMonths(1);
                    default -> tempNextDate;
                };
            }
        }
        return generated;
    }

    // ⭐ NEW HELPER METHOD
    // Create a Schedule easily from input values
    public static Schedule createSchedule(LocalDate startDate, String recurrenceType, String description, String type, double amount) {
        Transaction transaction = new Transaction(startDate, type.toUpperCase(), description, amount);
        return new Schedule(transaction, recurrenceType, startDate);
    }
}