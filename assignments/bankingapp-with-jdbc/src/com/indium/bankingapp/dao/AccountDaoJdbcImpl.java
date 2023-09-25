package com.indium.bankingapp.dao;

import com.indium.bankingapp.model.Account;
import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoJdbcImpl implements AccuntDao{

    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public AccountDaoJdbcImpl() {

    }

    public Connection getConnection() {
        try {
            if(conn == null) {
                MysqlDataSource datasource = new MysqlDataSource();
                datasource.setServerName("localhost");
                datasource.setDatabaseName("bankinapp");
                datasource.setUser("root");
                datasource.setPassword("1234");


                conn = datasource.getConnection();
                System.out.println("Connection created successfully. " + conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    @Override
    public Boolean create(Account account) {
        boolean status = false;
        try {

            String query = "INSERT INTO users(accountnumber, name, type, balance) values(?,?,?,?)";
            pstmt = getConnection().prepareStatement(query);
            pstmt.setInt(1, account.getAccountNumber());
            pstmt.setString(2, account.getAccHolderName());
            pstmt.setString(3, account.getAccType());
            pstmt.setInt(4, account.getBalance());

            status = pstmt.executeUpdate() > 0 ? true : false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public Boolean update(Account account) {
        boolean status = false;
        try {

            String query = "UPDATE users SET name = ?, type = ?, balance = ? WHERE accountnumber = ?";
            pstmt = getConnection().prepareStatement(query);
            pstmt.setString(1, account.getAccHolderName());
            pstmt.setString(2, account.getAccType());
            pstmt.setInt(3, account.getBalance());
            pstmt.setInt(4, account.getAccountNumber());

            System.out.println(query);

            status = pstmt.executeUpdate() > 0 ? true : false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public boolean delete(int accountNumber) {
        boolean status = false;
        try {
            stmt = getConnection().createStatement();

            String query = "DELETE FROM users WHERE accountnumber = " + accountNumber;

            status = stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public Account get(int accountNumber) {
        Account account = null;
        String query = "SELECT * FROM users WHERE accountnumber = " + accountNumber;
        try {
            stmt = getConnection().createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int accNumber = rs.getInt("accountnumber");
                String AccHolderName = rs.getString("name");
                String type = rs.getString("type");
                int balance = rs.getInt("balance");
                account = new Account(AccHolderName, balance,accNumber,type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public List<Account> getall() {
        List<Account> accounts = new ArrayList<>();
        try {
            stmt = getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM users");

            while (rs.next()) {
                int accountNumber = rs.getInt("accountnumber");
                String name = rs.getString("name");
                String type = rs.getString("type");
                int balance = rs.getInt("balance");
                accounts.add(new Account(name, balance,accountNumber,type));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }
}
