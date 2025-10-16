package com.pluralsight;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Scanner;

public class Reports {
    private static Scanner scanner = new Scanner(System.in);

    public static void display(ArrayList<Transaction> transactions) {
        while (true) {
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║           REPORTS SCREEN           ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back - Return to Ledger");
            System.out.print("\nEnter your choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    monthToDate(transactions);
                    break;
                case "2":
                    previousMonth(transactions);
                    break;
                case "3":
                    yearToDate(transactions);
                    break;
                case "4":
                    previousYear(transactions);
                    break;
                case "5":
                    searchByVendor(transactions);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void monthToDate(ArrayList<Transaction> transactions) {
        LocalDate now = LocalDate.now();
        LocalDate startOfMonth = now.withDayOfMonth(1);

        System.out.println("\n=== MONTH TO DATE ===");
        ArrayList<Transaction> filtered = filterByDateRange(transactions, startOfMonth, now);
        displayReport(filtered);
    }

    private static void previousMonth(ArrayList<Transaction> transactions) {
        YearMonth lastMonth = YearMonth.now().minusMonths(1);
        LocalDate startDate = lastMonth.atDay(1);
        LocalDate endDate = lastMonth.atEndOfMonth();

        System.out.println("\n=== PREVIOUS MONTH ===");
        ArrayList<Transaction> filtered = filterByDateRange(transactions, startDate, endDate);
        displayReport(filtered);
    }

    private static void yearToDate(ArrayList<Transaction> transactions) {
        LocalDate now = LocalDate.now();
        LocalDate startOfYear = now.withDayOfYear(1);

        System.out.println("\n=== YEAR TO DATE ===");
        ArrayList<Transaction> filtered = filterByDateRange(transactions, startOfYear, now);
        displayReport(filtered);
    }

    private static void previousYear(ArrayList<Transaction> transactions) {
        int lastYear = LocalDate.now().getYear() - 1;
        LocalDate startDate = LocalDate.of(lastYear, 1, 1);
        LocalDate endDate = LocalDate.of(lastYear, 12, 31);

        System.out.println("\n=== PREVIOUS YEAR ===");
        ArrayList<Transaction> filtered = filterByDateRange(transactions, startDate, endDate);
        displayReport(filtered);
    }

    private static void searchByVendor(ArrayList<Transaction> transactions) {
        System.out.print("\nEnter vendor name: ");
        String vendor = scanner.nextLine().trim();

        System.out.println("\n=== SEARCH RESULTS FOR: " + vendor + " ===");
        ArrayList<Transaction> filtered = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getVendor().toLowerCase().contains(vendor.toLowerCase())) {
                filtered.add(t);
            }
        }
        displayReport(filtered);
    }

    private static ArrayList<Transaction> filterByDateRange(ArrayList<Transaction> transactions,
                                                            LocalDate startDate, LocalDate endDate) {
        ArrayList<Transaction> filtered = new ArrayList<>();
        for (Transaction t : transactions) {
            LocalDate tDate = t.getDate();
            if (!tDate.isBefore(startDate) && !tDate.isAfter(endDate)) {
                filtered.add(t);
            }
        }
        return filtered;
    }

    private static void displayReport(ArrayList<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            // Sort by date (newest first)
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

            System.out.println("\nTotal transactions: " + transactions.size());
        }

        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
}