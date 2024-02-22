package com.svcet.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Customer {
    private String email;
    private String phone;
    private String userName;
    private String password;
    private double balance;
    private Bank bank;
    private Account account;
    private List<String> transactionHistory;

    public Customer(String email, String phone, String userName, String password, double balance, Bank bank, Account account) {
        this.email = email;
        this.phone = phone;
        this.userName = userName;
        this.password = password;
        this.balance = balance;
        this.bank = bank;
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            this.balance -= amount;
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(List<String> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }
}

class Bank {
    private String bankName;
    private String bankLocation;

    public Bank(String bankName, String bankLocation) {
        this.bankName = bankName;
        this.bankLocation = bankLocation;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankLocation() {
        return bankLocation;
    }

    public void setBankLocation(String bankLocation) {
        this.bankLocation = bankLocation;
    }
}

class Account {
    private String accountNo;
    private double accountBalance;
    private String accountType;

    public Account(String accountNo, double accountBalance, String accountType) {
        this.accountNo = accountNo;
        this.accountBalance = accountBalance;
        this.accountType = accountType;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}

public class BankManagementSystem {
    private Map<String, Customer> customers;
    private Scanner scanner;

    public BankManagementSystem() {
        customers = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    public void createAccount() {
        System.out.println("Enter email:");
        String email = scanner.nextLine();
        System.out.println("Enter phone:");
        String phone = scanner.nextLine();
        System.out.println("Enter username:");
        String userName = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        System.out.println("Enter initial deposit amount:");
        double initialDeposit = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        Bank bank = new Bank("XYZ Bank", "New York");
        Account account = new Account("123456789", initialDeposit, "Savings");

        Customer customer = new Customer(email, phone, userName, password, initialDeposit, bank, account);
        customers.put(email, customer);
        System.out.println("Account created successfully.");
    }

    public void depositAmount() {
        System.out.println("Enter email:");
        String email = scanner.nextLine();
        System.out.println("Enter amount to deposit:");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        Customer customer = customers.get(email);
        if (customer != null) {
            customer.deposit(amount);
            System.out.println("Amount deposited successfully.");
        } else {
            System.out.println("Customer not found.");
        }
    }

    public void transferAmount() {
        System.out.println("Enter sender's email:");
        String senderEmail = scanner.nextLine();
        System.out.println("Enter receiver's email:");
        String receiverEmail = scanner.nextLine();
        System.out.println("Enter amount to transfer:");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        Customer sender = customers.get(senderEmail);
        Customer receiver = customers.get(receiverEmail);

        if (sender != null && receiver != null) {
            if (sender.getBalance() >= amount) {
                sender.withdraw(amount);
                receiver.deposit(amount);
                System.out.println("Amount transferred successfully.");
            } else {
                System.out.println("Insufficient balance.");
            }
        } else {
            System.out.println("Sender or receiver not found.");
        }
    }

    public void getAccountInfo() {
        System.out.println("Enter email:");
        String email = scanner.nextLine();

        Customer customer = customers.get(email);
        if (customer != null) {
            System.out.println("Account Information:");
            System.out.println("Email: " + customer.getEmail());
            System.out.println("balance: "+customer.getBalance());
            // Print other information
        } else {
            System.out.println("Customer not found.");
        }
    }

    public static void main(String[] args) {
        BankManagementSystem bankSystem = new BankManagementSystem();
        while (true) {
            System.out.println("Welcome to the Bank Management System!");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Amount");
            System.out.println("3. Transfer Amount");
            System.out.println("4. Account Info");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = bankSystem.scanner.nextInt();
            bankSystem.scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    bankSystem.createAccount();
                    break;
                case 2:
                    bankSystem.depositAmount();
                    break;
                case 3:
                    bankSystem.transferAmount();
                    break;
                case 4:
                    bankSystem.getAccountInfo();
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
}
