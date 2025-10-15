package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;

// info needed
public class Transaction {
    private LocalDate date;
    private LocalTime time;
    private String description,vendor;
    private double amount;

// My constructors
    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }
    // My getters and Setters
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
// Add to CSV file in formatted form
public String toString(){
    return String.format("%s|%s|%s|%s|%.2f", date, time, description, vendor, amount);
}
    }

