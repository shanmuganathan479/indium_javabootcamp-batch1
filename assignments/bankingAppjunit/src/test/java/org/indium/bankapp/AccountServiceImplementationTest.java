package org.indium.bankapp;

import org.indium.bankapp.Dao.AccountDao;
import org.indium.bankapp.Model.Account;
import org.indium.bankapp.Service.AccountServiceImplement;
import org.junit.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class AccountServiceImplementationTest {

    private AccountServiceImplement accountService;
    private AccountDao accountDao;

    @Before
    public void setUp() {
        accountDao = mock(AccountDao.class);
        accountService = new AccountServiceImplement();
    }
    @Test
    public void createAccount() {
        Account account = new Account(1,"kaja", 1000,"savings");
        accountService.createAccount(account);
        assertEquals(2, accountService.accountIdCounter);
    }

    @Test
    public void testGetAllAccounts() {
        Account[] allAccounts = accountService.getAllAccounts();

        for (Account account : allAccounts) {
            System.out.println("Retrieved Account: " + account.getId() + ", " + account.getName() + ", " + account.getBalance() + ", " + account.getType());
        }

    }
    @Test
    public void testGetAccountById() {
        Account retrievedAccount = accountService.getAccountById(1);
        if (retrievedAccount != null) {
            System.out.println("Retrieved Account Details:");
            System.out.println("ID: " + retrievedAccount.getId());
            System.out.println("Name: " + retrievedAccount.getName());
            System.out.println("Balance: " + retrievedAccount.getBalance());
            System.out.println("Type: " + retrievedAccount.getType());
        } else {
            System.out.println("Account with ID " + 1 + " not found.");
        }

        assertNotNull(retrievedAccount);
    }
    @Test
    public void testUpdateBalance() {
        Account account = new Account(1, "kaja", 1000, "Savings");
      accountService.updateBalance(account, 6000);

    }

    @Test
    public void testDeleteAccount() {
        int accountId = 1;
        when(accountDao.delete(accountId)).thenReturn(true);
        accountService.deleteAccount(accountId);
    }


}
