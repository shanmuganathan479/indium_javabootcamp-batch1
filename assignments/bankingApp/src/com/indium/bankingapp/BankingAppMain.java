package com.indium.bankingapp;


import com.indium.bankingapp.service.AccountService;
import com.indium.bankingapp.model.Account;
import com.indium.bankingapp.service.AccountServiceArrImpl;

import java.util.Scanner;

public class BankingAppMain {
    static int noOfAccountsOpened = 1;
    static AccountService accountService = new AccountServiceArrImpl();
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args){

        Scanner getData = new Scanner(System.in);
        int i = 1;
        while(i>0){
            System.out.println("1.New Account");
            System.out.println("2.View Allaccounts");
            System.out.println("3.View Account");
            System.out.println("4.Update Account");
            System.out.println("5.Delete Account");
            System.out.println("6.Exit");
            System.out.println("Enter your choice : ");
            int userChoice = getData.nextInt();
            switch (userChoice){
                case 1: {
                    Account newAccount = captureAccountDetails();
                    boolean accountCreated = accountService.createAccount(newAccount);
                    if(accountCreated){
                        System.out.println("Account added successfully  AccountId is: "+noOfAccountsOpened);
                        System.out.println("Welcome."+newAccount.getAccHolderName());
                        noOfAccountsOpened++;
                    }
                    else {
                        System.out.println("storage full.");
                        System.out.println("Contact the owner");
                    }
                    break;
                }
                case 2:{
                    System.out.println("viewing all accounts");
                    Account[] allAccounts = accountService.getAllAccounts();
                    if(allAccounts.length >0){
                        System.out.println("All Accounts:");
                        System.out.println("| Account Id | Account Holder   | Account Balance |");
                        for (Account account : allAccounts) {
                            if (account != null) {
                                System.out.println("        "+account.getAccountId()+"     "+
                                        account.getAccHolderName()+"                 "+ account.getBalance());
                                System.out.println("--------------------------------------------------");
                            }
                        }
                    }else {
                        System.out.println("No records found");
                    }
                    break;
                }
                case 3: {
                    System.out.println("viewing an account");
                    System.out.print("Enter account id to view: ");
                    int viewAccountNumber = scanner.nextInt();
                    Account viewAccount = accountService.getAccount(viewAccountNumber);
                    if (viewAccount != null) {
                        System.out.println("Account Number: " + viewAccount.getAccountId());
                        System.out.println("Account Holder: " + viewAccount.getAccHolderName());
                        System.out.println("Account Balance: " + viewAccount.getBalance()+" Rupees");
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                }
                case 4 :{
                    System.out.print("Enter account id to update: ");
                    int updateAccountId = scanner.nextInt();
                    Account existingAccount = accountService.getAccount(updateAccountId);
                    if (existingAccount != null) {
                        Account updatedAccount = captureUpdateAccountDetails(updateAccountId,
                                existingAccount.getBalance());
                        boolean updated = accountService.updateAccount(updateAccountId, updatedAccount);
                        if (updated) {
                            System.out.println("Account updated successfully.");
                        } else {
                            System.out.println("Account update failed.");
                        }
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                }

                case 5:{
                    System.out.print("Enter account id to delete: ");
                    int deleteAccountNumber = scanner.nextInt();
                    Account deleteAccount = accountService.getAccount(deleteAccountNumber);
                    if (deleteAccount != null) {
                        boolean deleted = accountService.deleteAccount(deleteAccount);
                        if (deleted) {
                            System.out.println("Account deleted successfully.");
                        } else {
                            System.out.println("Account deletion failed.");
                        }
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                }
                case 6 :{
                    i =0;
                    break;
                }
                default:{
                    System.out.println("enter a valid option");
                }
            }
        }
    }

    public static Account captureAccountDetails(){
        String userName;
        double balance;
        System.out.println("enter your name : ");
        userName = scanner.next();
        System.out.println("enter initial balance : ");
        balance = scanner.nextDouble();
        return new Account(userName,balance,noOfAccountsOpened);
    }

    public static Account captureUpdateAccountDetails(int accountId, double balance) {
        String accountHolderName;
        while (true) {
            System.out.print("Update account holder's name: ");
            accountHolderName = scanner.nextLine();
            if (!accountHolderName.isEmpty()) {
                break;
            }
        }
        return new Account(accountHolderName, balance, accountId);
    }
}
