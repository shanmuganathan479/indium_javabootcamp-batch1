package com.indium.bankingapp.service;
import java.util.*;

import com.indium.bankingapp.model.Account;

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
}
