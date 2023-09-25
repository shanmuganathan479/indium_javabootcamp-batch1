package com.indium.bankingapp.service;

import com.indium.bankingapp.dao.AccountDaoJdbcImpl;
import com.indium.bankingapp.dao.AccuntDao;
import com.indium.bankingapp.model.Account;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AccountServiceImpl implements AccountService{
    static Scanner getData = new Scanner(System.in);
    AccuntDao accuntDao;

    public AccountServiceImpl() {

        accuntDao = new AccountDaoJdbcImpl();
    }
    @Override
    public boolean createAccount(Account account) {
        return accuntDao.create(account);
    }

    @Override
    public void updateAccount(int accountNumber) {
        Account account  = accuntDao.get(accountNumber);
        System.out.println("enter balance to update");
        int balance = getData.nextInt();
        account.setBalance(balance);
        accuntDao.update(account);
        System.out.println("account updated...");
    }

    @Override
    public void deleteAccount(int accountNumber) {
        accuntDao.delete(accountNumber);
        System.out.println("Account deleted...");
    }

    @Override
    public Account getAccount(int accountNumber) {
        System.out.println("Account Details:");
        System.out.println("---------------------------------------------------------");
        System.out.printf("%-15s %-20s %-15s %-15s%n", "Account Number", "Name", "Type", "Balance");
        System.out.println("---------------------------------------------------------");
        Account account  = accuntDao.get(accountNumber);
        System.out.format("%-15d %-20s %-15s %-10d\n",
                account.getAccountNumber(), account.getAccHolderName(), account.getAccType(), account.getBalance());
        return null;
    }

    @Override
    public void getAllAccounts() {
        List<Account> accounts = accuntDao.getall();
        System.out.println("Account Details:");
        System.out.println("---------------------------------------------------------");
        System.out.printf("%-15s %-20s %-15s %-15s%n", "Account Number", "Name", "Type", "Balance");
        System.out.println("---------------------------------------------------------");

        for (Account account : accounts) {
            System.out.format("%-15d %-20s %-15s %-10d\n",
                    account.getAccountNumber(), account.getAccHolderName(), account.getAccType(), account.getBalance());
        }
    }

    @Override
    public void printStatistics() {
        List<Account> accounts = accuntDao.getall();
        long accountsWithHighBalance = accounts.stream()
                .filter(account -> account.getBalance() > 100000)
                .count();

        System.out.println("Number of accounts with balance more than 1 lakh: " + accountsWithHighBalance);

        Map<String, Long> accountTypeCounts = accounts.stream()
                .collect(Collectors.groupingBy(Account::getAccType, Collectors.counting()));

        System.out.println("Number of accounts by account type: " + accountTypeCounts);

        accountTypeCounts.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> System.out.println("Account Type: " + entry.getKey() + ", Count: " + entry.getValue()));

        Map<String, Double> averageBalanceByType = accounts.stream()
                .collect(Collectors.groupingBy(
                        Account::getAccType,
                        Collectors.averagingDouble(Account::getBalance)
                ));

        System.out.println("Average balance by account type: " + averageBalanceByType);


    }

    @Override
    public void importData() {
        int counter =0;
        try(BufferedReader reader = new BufferedReader(new FileReader("./input/input.txt"))){
            String line;
            while((line = reader.readLine())!= null){
                String[] parts = line.split(",");
                String accHolderName = parts[0];
                int balance = Integer.parseInt(parts[1]);
                String accType = parts[3];
                int accNumber = Integer.parseInt(parts[2]);
                Account newAccount = new Account(accHolderName,balance,accNumber,accType);
                accuntDao.create(newAccount);
                counter++;
            }
            System.out.println("imported "+counter+" records");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void exportData() {
        int counter = 0;
        try(PrintWriter out = new PrintWriter(new FileWriter("./output/output.txt"))){
            List<Account> accounts = accuntDao.getall();
            for(Account account : accounts ){
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
