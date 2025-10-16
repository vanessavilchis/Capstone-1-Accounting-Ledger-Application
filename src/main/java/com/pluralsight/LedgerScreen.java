package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class LedgerScreen {
    private static Scanner scanner = new Scanner(System.in);

    public static void display(ArrayList<Transaction> transactions) {
        while (true) {
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║           LEDGER SCREEN            ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.println("A) All - Display all entries");
            System.out.println("D) Deposits - Display only deposits");
            System.out.println("P) Payments - Display only payments");
            System.out.println("R) Reports - Run reports");
            System.out.println("H) Home - Return to home screen");
            System.out.print("\nEnter your choice: ");

            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "A":
                    displayAll(transactions);
                    break;
                case "D":
                    displayDeposits(transactions);
                    break;
                case "P":
                    displayPayments(transactions);
                    break;
                case "R":
                    ReportsScreen.display(transactions);
                    break;
                case "H":
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayAll(ArrayList<Transaction> transactions) {
        System.out.println("\n=== ALL TRANSACTIONS (Newest First) ===");
        displayTransactions(transactions);
    }

    private static void displayDeposits(ArrayList<Transaction> transactions) {
        System.out.println("\n=== DEPOSITS ONLY (Newest First) ===");
        ArrayList<Transaction> deposits = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getAmount() > 0) {
                deposits.add(t);
            }
        }
        displayTransactions(deposits);
    }

    private static void displayPayments(ArrayList<Transaction> transactions) {
        System.out.println("\n=== PAYMENTS ONLY (Newest First) ===");
        ArrayList<Transaction> payments = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getAmount() < 0) {
                payments.add(t);
            }
        }
        displayTransactions(payments);
    }

    private static void displayTransactions(ArrayList<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        transactions.sort((t1, t2) -> {
            int dateCompare = t2.getDate().compareTo(t1.getDate());
            if (dateCompare != 0) return dateCompare;
            return t2.getTime().compareTo(t1.getTime());
        });

        System.out.printf("%-12s %-10s %-25s %-20s %15s\n",
                "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("-".repeat(90));

        for (Transaction t : transactions) {
            System.out.println(t);
        }

        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
}