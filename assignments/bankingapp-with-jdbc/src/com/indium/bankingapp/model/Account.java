package com.indium.bankingapp.model;

public class Account {
    private String accHolderName;
    private int balance;
    private String accType ;
    int accountNumber;

    public Account(String accHolderName, int balance, int accountNumber, String accType) {
        this.accHolderName = accHolderName;
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.accType = accType;
    }

    public String getAccHolderName() {
        return accHolderName;
    }

    public void setAccHolderName(String accHolderName) {
        this.accHolderName = accHolderName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
}
