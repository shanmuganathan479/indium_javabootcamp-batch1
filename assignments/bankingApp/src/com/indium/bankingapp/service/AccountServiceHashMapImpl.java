package com.indium.bankingapp.service;

import com.indium.bankingapp.model.Account;
import java.util.*;
public class AccountServiceHashMapImpl implements AccountService{
    private Map<Integer, Account> accountMap = new HashMap<>();
    private int accountIdCounter = 1;
    @Override
    public boolean createAccount(Account account) {
        int newAccountId = accountIdCounter++;
        account.setAccountId(newAccountId);
        accountMap.put(newAccountId, account);
        return true;
    }

    @Override
    public boolean updateAccount(int id, Account updatedAccount) {
        if (accountMap.containsKey(id)) {
            accountMap.put(id, updatedAccount);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteAccount(Account account) {
        int accountId = account.getAccountId();
        if (accountMap.containsKey(accountId)) {
            accountMap.remove(accountId);
            return true;
        }
        return false;
    }

    @Override
    public Account getAccount(int id) {
        return accountMap.get(id);
    }

    @Override
    public Account[] getAllAccounts() {
        return accountMap.values().toArray(new Account[0]);
    }
}
