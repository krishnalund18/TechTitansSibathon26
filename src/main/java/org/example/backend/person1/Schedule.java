package org.example.backend.person1;
import java.time.LocalDate;
public class Schedule {

        private Transaction transaction;
        private String recurrenceType;
        private LocalDate nextDate;


        public Schedule(Transaction transaction, String recurrenceType, LocalDate nextDate) {
            this.transaction = transaction;
            this.recurrenceType = recurrenceType.toUpperCase();
            this.nextDate = nextDate;
        }
        public Transaction getTransaction() {
            return transaction;
        }

        public String getRecurrenceType() {
            return recurrenceType;
        }

        public LocalDate getNextDate() {
            return nextDate;
        }


        public void setNextDate(LocalDate nextDate) {
            this.nextDate = nextDate;
        }

        public void calculateNextDate() {
            switch (recurrenceType) {
                case "DAILY":
                    nextDate = nextDate.plusDays(1);
                    break;
                case "WEEKLY":
                    nextDate = nextDate.plusWeeks(1);
                    break;
                case "MONTHLY":
                    nextDate = nextDate.plusMonths(1);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid recurrence type: " + recurrenceType);
            }
        }

        @Override
        public String toString() {
            return "Schedule{" +
                    "transaction=" + transaction +
                    ", recurrenceType='" + recurrenceType + '\'' +
                    ", nextDate=" + nextDate +
                    '}';
        }
    }


