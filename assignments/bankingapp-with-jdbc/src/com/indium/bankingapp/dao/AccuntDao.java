package com.indium.bankingapp.dao;

import com.indium.bankingapp.model.Account;

import java.util.List;

public interface AccuntDao {
    public Boolean create(Account account);
    public Boolean update(Account account);
    public boolean delete(int accountNumber);
    public Account get(int accountNumber);

    public List<Account> getall();
}
