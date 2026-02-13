package org.example;
import org.example.person2.UserAccount;
import org.example.person2.Transaction;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // 1️⃣ Create UserAccount
        UserAccount account = new UserAccount("Boss", 10000);

        // 2️⃣ Create Sample Transactions
        Transaction t1 = new Transaction(LocalDate.of(2026, 2, 13), "Income", "Salary", 5000);
        Transaction t2 = new Transaction(LocalDate.of(2026, 2, 14), "Expense", "Rent", 8000);
        Transaction t3 = new Transaction(LocalDate.of(2026, 2, 15), "Expense", "Subscription", 2000);
        Transaction t4 = new Transaction(LocalDate.of(2026, 2, 16), "Income", "Freelance", 3000);

        // 3️⃣ Add transactions to account
        account.addTransaction(t1);
        account.addTransaction(t2);
        account.addTransaction(t3);
        account.addTransaction(t4);

        // 4️⃣ Print account summary
        account.printSummary();

        // 5️⃣ Remove a transaction and recheck
        System.out.println("\nRemoving subscription transaction...");
        account.removeTransaction(t3);
        account.printSummary();

        // 6️⃣ Filter transactions by type
        List<Transaction> incomeTransactions = account.getTransactionsByType("Income");
        System.out.println("\nIncome Transactions:");
        for (Transaction t : incomeTransactions) {
            System.out.println(t.getDate() + " | " + t.getCategory() + " | " + t.getAmount());
        }
    }
}
