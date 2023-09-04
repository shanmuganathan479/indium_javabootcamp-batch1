package com.indium.bankingapp.model;

public class Account {
    private String accHolderName;
    private double balance;
    private String accType = "Savings";
    int accountId;

    public Account(String accHolderName, double balance, int accountId) {
        this.accHolderName = accHolderName;
        this.balance = balance;
        this.accountId = accountId;
    }

    public String getAccHolderName() {
        return accHolderName;
    }

    public void setAccHolderName(String accHolderName) {
        this.accHolderName = accHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccType() {
        return accType;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }
}
