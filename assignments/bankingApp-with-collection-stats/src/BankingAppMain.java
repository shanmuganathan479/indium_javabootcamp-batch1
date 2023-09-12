import java.util.*;
import com.indium.bankingapp.model.Account;
import com.indium.bankingapp.service.AccountService;
import com.indium.bankingapp.service.AccountServiceHashMapImpl;

public class BankingAppMain {
    static int noOfAccountsOpened = 1;
    static Scanner getData = new Scanner(System.in);
    static AccountService accountService = new AccountServiceHashMapImpl();
    public static void main(String[] args) {

        int i =1;
        do{
            System.out.println("\nBanking App Menu:");
            System.out.println("1. Add Account");
            System.out.println("2. View All Accounts");
            System.out.println("3. View Account");
            System.out.println("4. Update Account");
            System.out.println("5. Delete Account");
            System.out.println("6. Print Statistics");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice;
            try {
                choice = getData.nextInt();
                getData.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                getData.nextLine();
                continue;
            }

            switch (choice){
                case 1 :{
                    Account newAccount = captureAccountDetails();
                    boolean accountCreated = accountService.createAccount(newAccount);
                    if(accountCreated){
                        System.out.println("account added successfully with AccountNumber : "+newAccount.getAccountNumber());
                        System.out.println("welcome Dear."+newAccount.getAccHolderName());
                        noOfAccountsOpened++;
                    }
                    else {
                        System.out.println("Not able to open the account.");
                        System.out.println("Contact the administrator");
                    }
                    break;
                }

                case 2:{
                    accountService.getAllAccounts();
                    break;
                }
                case 3:{
                    System.out.println("viewing an account");
                    System.out.print("Enter account number to view: ");
                    int viewAccountNumber = getData.nextInt();
                    accountService.getAccount(viewAccountNumber);
                    break;
                }
                case 4:{
                    System.out.print("Enter account number: ");
                    int accountNumber = getData.nextInt();
                    accountService.updateAccount(accountNumber);
                    break;

                }
                case 5:{
                    System.out.print("Enter account number: ");
                    int accountNumber = getData.nextInt();
                    accountService.deleteAccount(accountNumber);
                    break;
                }
                case 6:{
                    accountService.printStatistics();
                    break;
                }
                case 7:{
                    i = 7;
                    break;
                }

            }
        }while(i !=7);

    }

    public static Account captureAccountDetails(){
        String userName;
        double balance;
        String accType;
        int accountNumber;
        System.out.println("enter your name : ");
        userName = getData.next();
        System.out.println("enter initial balance : ");
        balance = getData.nextDouble();
        System.out.println("enter the account number");
        accountNumber = getData.nextInt();
        System.out.println("enter account type");
        System.out.println("1.savings");
        System.out.println("2.current");
        int choice = getData.nextInt();
        switch (choice){
            case 1 : {
                accType = "savings";
                break;
            }
            case 2 :{
                accType = "current";
                break;
            }
            default:{
                accType = "savings";
                System.out.println("Wrong option");
                System.out.println("savings is taken as default choice");
            }
        }
        return new Account(userName,balance,accountNumber, accType);
    }
}