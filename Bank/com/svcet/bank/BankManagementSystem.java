package com.svcet.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Customer {
    private String name;
    private String email;
    private String address;
    private double balance;

    public Customer(String name, String email, String address, double balance) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit successful. New balance: " + balance);
    }

    public void transfer(Customer recipient, double amount) {
        if (balance >= amount) {
            balance -= amount;
            recipient.deposit(amount);
            System.out.println("Transfer successful. New balance: " + balance);
        } else {
            System.out.println("Insufficient funds for transfer.");
        }
    }

    public void displayAccountInfo() {
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Address: " + address);
        System.out.println("Balance: " + balance);
    }
}

public class BankManagementSystem {
    private static Map<String, Customer> customerDatabase = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Welcome to the Bank Management System!");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Amount");
            System.out.println("3. Transfer Amount");
            System.out.println("4. Account Info");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    depositAmount();
                    break;
                case 3:
                    transferAmount();
                    break;
                case 4:
                    getAccountInfo();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter initial deposit amount: ");
        double initialDeposit = scanner.nextDouble();

        Customer customer = new Customer(name, email, address, initialDeposit);
        customerDatabase.put(email, customer);
        System.out.println("Account created successfully.");
    }

    private static void depositAmount() {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        Customer customer = customerDatabase.get(email);
        if (customer != null) {
            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();
            customer.deposit(amount);
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void transferAmount() {
        System.out.print("Enter sender email: ");
        String senderEmail = scanner.nextLine();
        Customer sender = customerDatabase.get(senderEmail);
        if (sender != null) {
            System.out.print("Enter recipient email: ");
            String recipientEmail = scanner.nextLine();
            Customer recipient = customerDatabase.get(recipientEmail);
            if (recipient != null) {
                System.out.print("Enter transfer amount: ");
                double amount = scanner.nextDouble();
                sender.transfer(recipient, amount);
            } else {
                System.out.println("Recipient not found.");
            }
        } else {
            System.out.println("Sender not found.");
        }
    }

    private static void getAccountInfo() {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        Customer customer = customerDatabase.get(email);
        if (customer != null) {
            customer.displayAccountInfo();
        } else {
            System.out.println("Customer not found.");
        }
    }
}

