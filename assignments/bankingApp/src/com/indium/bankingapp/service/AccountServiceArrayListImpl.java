package com.indium.bankingapp.service;
import com.indium.bankingapp.model.Account;

import java.util.*;


public class AccountServiceArrayListImpl implements AccountService{

    private ArrayList<Account> accounts = new ArrayList<>();
    @Override
    public boolean createAccount(Account account) {
        accounts.add(account);
        return true;
    }


    @Override
    public boolean updateAccount(int id, Account updatedAccount) {
//        return false;
        for (int i = 0; i < accounts.size(); i++) {
            Account currentAccount = accounts.get(i);
            if (currentAccount.getAccountId() == id) {
                // Replace the old account with the updated account
                accounts.set(i, updatedAccount);
                return true;
            }
        }
        return false; // Account not found
    }

    @Override
    public boolean deleteAccount(Account account) {
//        return false;
        return accounts.remove(account);
    }

    @Override
    public Account getAccount(int id) {
//        return null;
        for (Account account : accounts) {
            if (account.getAccountId() == id) {
                return account;
            }
        }
        return null; // Account not found
    }

    @Override
    public Account[] getAllAccounts() {
//        return new Account[0];
//        return new ArrayList<>(accounts);
        return accounts.toArray(new Account[0]);
    }
}
