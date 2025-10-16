package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountingLedgerApp {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Transaction> transactions;

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║   WELCOME TO ACCOUNTING LEDGER APPLICATION    ║");
        System.out.println("╚════════════════════════════════════════════════╝");

        transactions = FileManager.loadTransactions();
        System.out.println("Loaded " + transactions.size() + " existing transactions.\n");

        displayHomeScreen();
    }

    private static void displayHomeScreen() {
        while (true) {
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║           HOME SCREEN              ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.print("\nEnter your choice: ");

            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "D":
                    addDeposit();
                    break;
                case "P":
                    makePayment();
                    break;
                case "L":
                    LedgerScreen.display(transactions);
                    break;
                case "X":
                    System.out.println("\n╔════════════════════════════════════╗");
                    System.out.println("║     Thank you for using our app!   ║");
                    System.out.println("║            Goodbye!                ║");
                    System.out.println("╚════════════════════════════════════╝");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addDeposit() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║           ADD DEPOSIT              ║");
        System.out.println("╚════════════════════════════════════╝");

        LocalDate date = getDateInput();
        LocalTime time = getTimeInput();
        String description = getStringInput("Enter description: ");
        String vendor = getStringInput("Enter vendor: ");
        double amount = getAmountInput("Enter deposit amount: ");

        amount = Math.abs(amount);

        Transaction transaction = new Transaction(date, time, description, vendor, amount);
        transactions.add(transaction);
        FileManager.saveTransaction(transaction);

        System.out.println("\n✓ Deposit added successfully!");
    }

    private static void makePayment() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║          MAKE PAYMENT              ║");
        System.out.println("╚════════════════════════════════════╝");

        LocalDate date = getDateInput();
        LocalTime time = getTimeInput();
        String description = getStringInput("Enter description: ");
        String vendor = getStringInput("Enter vendor: ");
        double amount = getAmountInput("Enter payment amount: ");

        amount = -Math.abs(amount);

        Transaction transaction = new Transaction(date, time, description, vendor, amount);
        transactions.add(transaction);
        FileManager.saveTransaction(transaction);

        System.out.println("\n✓ Payment recorded successfully!");
    }

    private static LocalDate getDateInput() {
        while (true) {
            System.out.print("Enter date (YYYY-MM-DD) or press Enter for today: ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                return LocalDate.now();
            }

            try {
                return LocalDate.parse(input);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }
    }

    private static LocalTime getTimeInput() {
        while (true) {
            System.out.print("Enter time (HH:MM:SS) or press Enter for current time: ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                return LocalTime.now();
            }

            try {
                return LocalTime.parse(input, DateTimeFormatter.ofPattern("HH:mm:ss"));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format. Please use HH:MM:SS.");
            }
        }
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static double getAmountInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please enter a valid number.");
            }
        }
    }
}