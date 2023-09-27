package org.indium.bankapp.Model;

public class Account {

    private int id;
    private String name;
    private int balance;
    private String type;

    public Account(int id, String name, int balance, String type) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.type = type;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Override
    public String toString() {
        return "Account ID: " + id +
                "\nAccount Name: " + name +
                "\nBalance: $" + balance +
                "\nAccount Type: " + type;
    }


}
