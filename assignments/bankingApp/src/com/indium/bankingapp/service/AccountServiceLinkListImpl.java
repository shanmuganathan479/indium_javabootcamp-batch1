package com.indium.bankingapp.service;

import com.indium.bankingapp.model.Account;
import java.util.*;

public class AccountServiceLinkListImpl implements AccountService{
//    private LinkedList<Account> accounts = new ArrayList<>();
    private  List<Account> accounts = new LinkedList<>();
    @Override
    public boolean createAccount(Account account) {
        accounts.add(account);
        return true;
    }

    @Override
    public boolean updateAccount(int id, Account updatedAccount) {
        ListIterator<Account> iterator = accounts.listIterator();
        while (iterator.hasNext()) {
            Account currentAccount = iterator.next();
            if (currentAccount.getAccountId() == id) {
                iterator.set(updatedAccount);
                return true;
            }
        }
        return false; // Account not found
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
//        return new Account[0];
        return accounts.toArray(new Account[0]);
    }
}
