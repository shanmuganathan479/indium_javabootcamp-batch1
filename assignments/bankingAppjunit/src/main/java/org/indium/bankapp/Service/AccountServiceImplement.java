package org.indium.bankapp.Service;

import org.indium.bankapp.Dao.AccountDao;
import org.indium.bankapp.Dao.AccountDaoJdbcImplementation;
import org.indium.bankapp.Model.Account;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class AccountServiceImplement implements AccountService {

    private HashMap<Integer, Account> accountMap = new HashMap<>();
    static Scanner getData = new Scanner(System.in);
    AccountDao accountDao;
    public int accountIdCounter = 1;

    public AccountServiceImplement(){
        accountDao = new AccountDaoJdbcImplementation();
    }

    @Override
    public void createAccount(Account account) {
        int id = accountIdCounter++;
        account.setId(id);
        accountDao.create(account);
    }



    @Override
    public Account[] getAllAccounts() {
        return accountDao.getall().toArray(new Account[0]);
    }

    @Override
    public Account getAccountById(int id) {
        return accountDao.get(id);


    }

    @Override
    public void updateBalance(Account account, int newBalance) {
        if (account != null) {
            account.setBalance(newBalance);
            accountDao.update(account);
            System.out.println("Account balance updated successfully.");
        } else {
            System.out.println("Account balance not updated");
        }
    }


    @Override
    public void deleteAccount(int id) {
        accountDao.delete(id);
    }

    @Override
    public long countAccountsAboveOneLac() {
        return accountMap.values()
                .stream()
                .filter(account -> account.getBalance() > 100000)
                .count();
    }

    @Override
    public Map<String,Long> countAccountsByType() {
        return accountMap.values()
                .stream()

                .collect(Collectors.groupingBy(
                        Account::getType,
                        Collectors.counting()
                ));
    }

    @Override
    public Map<String, Long> countAndSortAccountsByType() {
        return accountMap.values()
                .stream()
                .collect(Collectors.groupingBy(
                        Account::getType,
                        TreeMap::new,
                        Collectors.counting()
                ));
    }

    public Map<String, Double> calculateAvgBalanceByType() {
        return accountMap.values()
                .stream()
                .collect(Collectors.groupingBy(
                        Account::getType,
                        Collectors.averagingInt(Account::getBalance)
                ));
    }
    @Override
    public Map<Integer, String> getAccountIdsByName(String partialName) {
        return accountMap.values()
                .stream()
                .filter(account -> account.getName().contains(partialName))
                .collect(Collectors.toMap(
                        Account::getId,
                        Account::getName
                ));

    }
    @Override
    public void importProducts() {
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("./input/import.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                int balance = Integer.parseInt(parts[2].trim());
                String type = parts[3].trim();
                Account account = new Account(id,name,balance,type);
                accountDao.create(account);
                count++;

            }
            System.out.println("Data Imported");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void exportProducts() {
        int count = 0;
        try(PrintWriter out = new PrintWriter(new FileWriter("./output/export.txt"))){
            List<Account> accounts= accountDao.getall();
            for(Account account : accounts){
                StringBuilder accountrecord = new StringBuilder();
                accountrecord.append(account.getId())
                        .append(",")
                        .append(account.getName())
                        .append(",")
                        .append(account.getBalance())
                        .append(",")
                        .append(account.getType())
                        .append("\n");
                out.write(accountrecord.toString());
                count++;
            }
            System.out.println("Data exported "+count);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }


}