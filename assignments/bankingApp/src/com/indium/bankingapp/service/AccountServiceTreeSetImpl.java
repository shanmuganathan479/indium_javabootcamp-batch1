package com.indium.bankingapp.service;

import com.indium.bankingapp.model.Account;

import java.util.*;

public class AccountServiceTreeSetImpl implements AccountService{
    private Set<Account> accounts = new TreeSet<>();
    @Override
    public boolean createAccount(Account account) {
//        accounts.add(account);
//        return true;
        return accounts.add(account);
    }

    @Override
    public boolean updateAccount(int id, Account updatedAccount) {
        if (deleteAccountById(id)) {
            return accounts.add(updatedAccount);
        }
        return false;
    }

    private boolean deleteAccountById(int id) {
        for (Account account : accounts) {
            if (account.getAccountId() == id) {
                return accounts.remove(account);
            }
        }
        return false;
    }

    @Override
    public boolean deleteAccount(Account account) {
        return accounts.remove(account);
    }

    @Override
    public Account getAccount(int id) {
        for (Account account : accounts) {
            if (account.getAccountId() == id) {
                return account;
            }
        }
        return null;
    }

    @Override
    public Account[] getAllAccounts() {
        return accounts.toArray(new Account[0]);
    }
}
