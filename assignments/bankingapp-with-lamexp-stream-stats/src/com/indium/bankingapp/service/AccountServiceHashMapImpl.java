package com.indium.bankingapp.service;

import com.indium.bankingapp.model.Account;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.*;

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

    @Override
    public void printStatistics() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }
        long countAccountsOver1Lac = accounts.values()
                .stream()
                .filter(account -> account.getBalance() > 100000)
                .count();
        System.out.println("Accounts having more than 1 Lakh balance: "+countAccountsOver1Lac);

        Map<String, Long> accountTypeCounts = accounts.values()
                .stream()
                .collect(Collectors.groupingBy(Account::getAccType, Collectors.counting()));
        System.out.println(" Show no of account by account type:");
        accountTypeCounts.forEach((accountType, count) -> System.out.println(accountType + ": " + count));


        accountTypeCounts.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> System.out.println("Account Type: " + entry.getKey() + ", Count: " + entry.getValue()));

        Map<String, Double> avgBalanceByAccountType = accounts.values()
                .stream()
                .collect(Collectors.groupingBy(Account::getAccType, Collectors.averagingDouble(Account::getBalance)));

        avgBalanceByAccountType.forEach((accountType, avgBalance) -> System.out.println("Account Type: " + accountType + ", Avg Balance: " + avgBalance));



    }

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
}
