package org.indium.bankapp.Dao;



import com.mysql.cj.jdbc.MysqlDataSource;
import org.indium.bankapp.Model.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoJdbcImplementation implements AccountDao {

    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public AccountDaoJdbcImplementation() {

    }

    public Connection getConnection() {
        try {
            if(conn == null) {
                MysqlDataSource datasource = new MysqlDataSource();
                datasource.setServerName("localhost");
                datasource.setDatabaseName("bankapp");
                datasource.setUser("root");
                datasource.setPassword("Kaja@0005");


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

            String query = "INSERT INTO accountdetails(id,name,balance,type) values(?,?,?,?)";
            pstmt = getConnection().prepareStatement(query);
            pstmt.setInt(1, account.getId());
            pstmt.setString(2, account.getName());
            pstmt.setInt(3, account.getBalance());
            pstmt.setString(4, account.getType());
            System.out.println("Data created and Stored successfully");

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

            String query = "UPDATE accountdetails SET name = ?, type = ?, balance = ? WHERE id = ?";
            pstmt = getConnection().prepareStatement(query);
            pstmt.setString(1, account.getName());
            pstmt.setString(2, account.getType());
            pstmt.setInt(3, account.getBalance());
            pstmt.setInt(4, account.getId());

            System.out.println(query);

            status = pstmt.executeUpdate() > 0 ? true : false;
            System.out.println("Data  update successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public boolean delete(int id) {
        boolean status = false;
        try {
            stmt = getConnection().createStatement();

            String query = "DELETE FROM accountdetails WHERE id = " + id;
            System.out.println("Account "+id+"deleted.");
            status = stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public Account get(int id) {
        Account account = null;
        String query = "SELECT * FROM accountdetails WHERE id = " + id;
        try {
            stmt = getConnection().createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int accid = rs.getInt("id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                int balance = rs.getInt("balance");
                account = new Account(accid,name, balance,type);
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
            rs = stmt.executeQuery("SELECT * FROM accountdetails");

            while (rs.next()) {
                int accid = rs.getInt("id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                int balance = rs.getInt("balance");
                accounts.add(new Account(accid,name, balance,type));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }
}





