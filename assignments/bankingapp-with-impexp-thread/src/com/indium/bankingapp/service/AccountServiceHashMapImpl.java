package com.indium.bankingapp.service;

import com.indium.bankingapp.model.Account;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AccountServiceHashMapImpl implements AccountService{
    private static Map<Integer, Account> accounts = new HashMap<>();
    @Override
    public boolean createAccount(Account account) {
        accounts.put(account.getAccountNumber(),account);
        return true;
    }

    @Override
    public void updateAccount(int accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter new balance: ");
            double newBalance = scanner.nextDouble();
            Account newAccount = new Account( account.getAccHolderName(), newBalance,accountNumber,account.getAccType());
            accounts.put(accountNumber, newAccount);
            System.out.println("Account updated successfully.");
        } else {
            System.out.println("Account not found.");
        }

        return;
    }

    @Override
    public void deleteAccount(int accountNumber) {
        if (accounts.containsKey(accountNumber)) {
            accounts.remove(accountNumber);
            System.out.println("Account deleted successfully.");
            return;
        } else {
            System.out.println("Account not found.");
            return;
        }
    }

    @Override
    public Account getAccount(int accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            System.out.println("Account Details:");
            System.out.printf("%-15s %-20s %-15s %-10s\n", "Account Number", "Account Type", "Account Name", "Balance");
            System.out.printf("%-15d %-20s %-15s %-10.2f\n", accountNumber, account.getAccType(), account.getAccHolderName(), account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
        return null;
    }

    @Override
    public void getAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }
        System.out.println("All Accounts:");
        System.out.printf("%-15s %-20s %-15s %-10s\n", "Account Number", "Account Type", "Account Name", "Balance");
        for (Map.Entry<Integer, Account> entry : accounts.entrySet()) {
            int accountNumber = entry.getKey();
            Account account = entry.getValue();
            String accountType = account.getAccType();
            String accountName = account.getAccHolderName();
            double balance = account.getBalance();

            System.out.printf("%-15d %-20s %-15s %-10.2f\n", accountNumber, accountType, accountName, balance);
        }
        return;
    }

    public void printStatistics(){
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }

        double totalBalance = 0;
        int countAccountsAbove1Lac = 0;
        Map<String, Integer> accountTypeCount = new HashMap<>();
        Map<String, Double> accountTypeTotalBalance = new HashMap<>();
        for (Account account : accounts.values()) {
            totalBalance += account.getBalance();

            if (account.getBalance() > 100000) {
                countAccountsAbove1Lac++;
            }

            String accountType = account.getAccType();
            accountTypeCount.put(accountType, accountTypeCount.getOrDefault(accountType, 0) + 1);
            accountTypeTotalBalance.put(accountType, accountTypeTotalBalance.getOrDefault(accountType, 0.0) + account.getBalance());

            }
        System.out.println("a] No of accounts which has balance more than 1 lac: " + countAccountsAbove1Lac);

        System.out.println("b] Show no of account by account type:");
        for (Map.Entry<String, Integer> entry : accountTypeCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("c] Show no of accounts by account type with sorting:");
        accountTypeCount.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

        System.out.println("d] Show avg balance by account type:");
        for (Map.Entry<String, Double> entry : accountTypeTotalBalance.entrySet()) {
            String accountType = entry.getKey();
            double totalBalanceByType = entry.getValue();
            int countByType = accountTypeCount.get(accountType);
            double avgBalance = totalBalanceByType / countByType;
            System.out.println(accountType + ": " + avgBalance);
        }
    }

//    old code for importing data without using any threads
    public synchronized void importData(){
        int counter =0;
        try(BufferedReader reader = new BufferedReader(new FileReader("./input/input.txt"))){
            String line;
            while((line = reader.readLine())!= null){
                String[] parts = line.split(",");
                String accHolderName = parts[0];
                double balance = Double.parseDouble(parts[1]);
                String accType = parts[3];
                int accNumber = Integer.parseInt(parts[2]);
                Account newAccount = new Account(accHolderName,balance,accNumber,accType);
                accounts.put(accNumber,newAccount);
                counter++;
            }
            System.out.println("imported "+counter+" records");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

//    public void importData(){
//        Runnable obj1 = new Runnable() {
//            @Override
//            public void run() {
//                int counter =0;
//                try(BufferedReader reader = new BufferedReader(new FileReader("./input/input.txt"))){
//                    String line;
//                    while((line = reader.readLine())!= null){
//                        String[] parts = line.split(",");
//                        String accHolderName = parts[0];
//                        double balance = Double.parseDouble(parts[1]);
//                        String accType = parts[3];
//                        int accNumber = Integer.parseInt(parts[2]);
//                        Account newAccount = new Account(accHolderName,balance,accNumber,accType);
//                        accounts.put(accNumber,newAccount);
//                        counter++;
//                    }
//                    System.out.println("imported "+counter+" records");
//                }catch(Exception e){
//                    System.out.println(e.getMessage());
//                }
//
//            }
//        };
//        Thread t1 = new Thread(obj1);
//        t1.setName("Import thread");
//        t1.start();
//        System.out.println("importing data using "+t1.getName());
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    old method for export data without using seperate thread
    public void exportData(){
        int counter = 0;
        try(PrintWriter out = new PrintWriter(new FileWriter("./output/output.txt"))){
            for(Account account : accounts.values() ){
                StringBuilder accountRecord = new StringBuilder();
                accountRecord.append(account.getAccHolderName())
                        .append(",")
                        .append(account.getBalance())
                        .append(",")
                        .append(account.getAccountNumber())
                        .append(",")
                        .append(account.getAccType())
                        .append("\n");
                out.write(accountRecord.toString());
                counter++;
            }
            System.out.println("exported "+counter+" account details");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

//    public void exportData(){
//        Runnable obj1 = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//                int counter = 0;
//                try(PrintWriter out = new PrintWriter(new FileWriter("./output/output.txt"))){
//                    for(Account account : accounts.values() ){
//                        StringBuilder accountRecord = new StringBuilder();
//                        accountRecord.append(account.getAccHolderName())
//                                .append(",")
//                                .append(account.getBalance())
//                                .append(",")
//                                .append(account.getAccountNumber())
//                                .append(",")
//                                .append(account.getAccType())
//                                .append("\n");
//                        out.write(accountRecord.toString());
//                        counter++;
//                    }
//                    System.out.println("exported "+counter+" account details");
//                }catch(Exception e){
//                    System.out.println(e.getMessage());
//                }
//            }
//        };
//        Thread t2 = new Thread(obj1);
//        t2.setName("Export thread");
//        t2.start();
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
