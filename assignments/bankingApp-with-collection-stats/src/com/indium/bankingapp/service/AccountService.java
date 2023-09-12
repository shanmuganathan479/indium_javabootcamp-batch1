package com.indium.bankingapp.service;
import com.indium.bankingapp.model.Account;

public interface AccountService {

    public boolean createAccount(Account account);
    public void updateAccount(int accountNumber);
    public void deleteAccount(int accountNumber);
    public Account getAccount(int id);
    public void getAllAccounts();
    public void printStatistics();

}
