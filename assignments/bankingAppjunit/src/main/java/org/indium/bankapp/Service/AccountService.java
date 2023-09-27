package org.indium.bankapp.Service;

import org.indium.bankapp.Model.Account;

import java.util.Map;

public interface AccountService {

    void createAccount(Account account);
    Account[] getAllAccounts();
    Account getAccountById(int id);
    void updateBalance(Account account, int newBalance);
    void deleteAccount(int id);
    void importProducts();
    void exportProducts();
    long countAccountsAboveOneLac();
    Map<String, Long> countAccountsByType();
    Map<String, Long>countAndSortAccountsByType();
    Map<String, Double> calculateAvgBalanceByType();
    Map<Integer, String> getAccountIdsByName(String partialName);



}
