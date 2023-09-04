package com.indium.bankingapp.service;
import com.indium.bankingapp.model.Account;

public class AccountServiceArrImpl implements AccountService{

    private Account[] accounts = new Account[10];
    private int count = 1;
    @Override
    public boolean createAccount(Account account) {
        if(count < accounts.length) {
            accounts[count] = account;
            count++;
            return true;
        }
        return false;
    }

    @Override
    public boolean updateAccount(int id, Account updatedAccount) {
        for (int i = 0; i < count; i++) {
            if (accounts[i] != null && accounts[i].getAccountId() == id) {
                accounts[i] = updatedAccount;
                return true;
            }
        }
        return false; // Account not found
    }

    @Override
    public boolean deleteAccount(Account account) {
        for (int i = 0; i < count; i++) {
            if (accounts[i] != null && accounts[i].getAccountId() == account.getAccountId()) {
                for (int j = i; j < count - 1; j++) {
                    accounts[j] = accounts[j + 1];
                }
                accounts[count - 1] = null;
                count--;
                return true;
            }
        }
        return false;
    }


    @Override
    public Account getAccount(int id) {
        for (int i = 0; i < count; i++) {
            if (accounts[i] != null && accounts[i].getAccountId() == id) {
                return accounts[i];
            }
        }
        return null; // Account not found
    }


    @Override
    public Account[] getAllAccounts() {
        Account[] allAccounts = new Account[count];
        System.arraycopy(accounts, 0, allAccounts, 0, count);
        return allAccounts;
    }
}
