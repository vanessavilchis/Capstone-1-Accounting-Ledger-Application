package com.pluralsight;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class AddDeposit {
    public static void deposit() throws IOException {
        Scanner scan = new Scanner(System.in);
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter("src/main/resources/transactions.csv", true));

        System.out.println("Enter the date of the deposit (yyyy-mm-dd)");
        String date = scan.nextLine();
        System.out.println("Enter the time of the deposit (hh:mm:dd)");
        String time = scan.nextLine();
        System.out.println("Enter the description of the deposit");
        String description = scan.nextLine();
        System.out.println("Enter the provider of the deposit");
        String provider = scan.nextLine();
        System.out.println("Enter the amount of the deposit");
        String amount = scan.nextLine();

        buffWrite.write(date + "|" + time + "|" + description + "|" + provider + "|" + amount);
        buffWrite.close();
    }
}