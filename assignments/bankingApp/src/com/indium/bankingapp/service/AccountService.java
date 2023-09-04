package com.indium.bankingapp.service;
import com.indium.bankingapp.model.Account;

public interface AccountService {

    public boolean createAccount(Account account);
    public boolean updateAccount(int id,Account account);
    public boolean deleteAccount(Account account);
    public Account getAccount(int id);
    public Account[] getAllAccounts();

}
