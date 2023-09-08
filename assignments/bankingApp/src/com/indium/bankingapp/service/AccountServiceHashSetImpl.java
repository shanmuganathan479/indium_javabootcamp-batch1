package com.indium.bankingapp.service;

import com.indium.bankingapp.model.Account;
import java.util.HashSet;
import java.util.Set;

public class AccountServiceHashSetImpl implements AccountService {

    private Set<Account> accounts = new HashSet<>();

    @Override
    public boolean createAccount(Account account) {
        return accounts.add(account);
    }

    @Override
    public boolean updateAccount(int id, Account updatedAccount) {
        if (deleteAccountById(id)) {
            return accounts.add(updatedAccount);
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
        return new HashSet<>(accounts).toArray(new Account[0]);
    }

    private boolean deleteAccountById(int id) {
        for (Account account : accounts) {
            if (account.getAccountId() == id) {
                return accounts.remove(account);
            }
        }
        return false;
    }
}
