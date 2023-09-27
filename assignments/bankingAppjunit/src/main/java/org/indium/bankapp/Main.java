package org.indium.bankapp;

import org.indium.bankapp.Model.Account;
import org.indium.bankapp.Service.AccountServiceImplement;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        AccountServiceImplement accountService = new AccountServiceImplement();

        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("Account Management Menu:");
            System.out.println("1. Create Account");
            System.out.println("2. View All Accounts");
            System.out.println("3. View Account by ID");
            System.out.println("4. Update Account Balance");
            System.out.println("5. Delete Account");
            System.out.println("6. Print Statistics");
            System.out.println("7. Import Data");
            System.out.println("8. Export Data");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:

                    System.out.print("Enter Account Id: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter Account Name: ");
                    String name = scanner.next();
                    System.out.print("Enter Account Balance: ");
                    int balance = scanner.nextInt();
                    System.out.print("Enter Account Type: ");
                    String type = scanner.next();
                    Account newAccount = new Account(id, name, balance, type);
                    accountService.createAccount(newAccount);

                    break;
                case 2:

                    Account[] allAccounts = accountService.getAllAccounts();
                    for (Account account : allAccounts) {
                        System.out.println(account);

                    }
                    break;
                case 3:

                    System.out.print("Enter Account Id to view: ");
                    int viewId = scanner.nextInt();
                    Account viewAccount = accountService.getAccountById(viewId);
                    if (viewAccount != null) {
                        System.out.println(viewAccount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 4:

                    System.out.print("Enter Account ID to update balance: ");
                    int updateId = scanner.nextInt();
                    Account updateAccount = accountService.getAccountById(updateId);
                    if (updateAccount != null) {
                        System.out.print("Enter the new amount : ");
                        int newBalance = scanner.nextInt();
                        accountService.updateBalance(updateAccount, newBalance);
                    } else {
                        System.out.println("Update failed.");
                    }
                    break;

                case 5:

                    System.out.print("Enter Account Id to delete: ");
                    int deleteId = scanner.nextInt();
                    accountService.deleteAccount(deleteId);
                    break;
                case 6:
                    printStatistics(accountService);
                    break;
                case 7:
                    accountService.importProducts();
                    break;
                case 8:
                    accountService.exportProducts();
                    break;
                case 9:
                    System.out.println("Exiting the application.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 9);


    }

    private static void printStatistics(AccountServiceImplement accountService) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Statistics:");
        Long accountsAboveOneLac = accountService.countAccountsAboveOneLac();
        System.out.println("No of accounts which have a balance more than 1 lac: " + accountsAboveOneLac);

        System.out.println("No of account by account type:");
        accountService.countAccountsByType().forEach((type, count) ->
                System.out.println(type + ": " + count)
        );

        System.out.println(" No of accounts by account type with sorting:");
        accountService.countAndSortAccountsByType().forEach((type, count) ->
                System.out.println(type + ": " + count)
        );
        System.out.println("Avg balance by account type:");
        accountService.calculateAvgBalanceByType().forEach((type, avgBalance) ->
                System.out.println(type + ": " + avgBalance)
        );
        System.out.print("Enter the partial name: ");
        String partialName = sc.nextLine();
        System.out.println("Listing the account ids whose account name contains '" + partialName + "':");
        accountService.getAccountIdsByName(partialName).forEach((accountId, accountName) -> {
            System.out.println("Account ID: " + accountId + ", Account Name: " + accountName);
        });

    }
}

