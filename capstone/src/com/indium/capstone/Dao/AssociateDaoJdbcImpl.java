package com.indium.capstone.Dao;

import java.sql.*;

import com.indium.capstone.model.Associate;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.util.*;

public class AssociateDaoJdbcImpl implements AssociateDao {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public AssociateDaoJdbcImpl() {

    }

    public Connection getConnection() {
        try {
            if(conn == null) {
                MysqlDataSource datasource = new MysqlDataSource();
                datasource.setServerName("localhost");
                datasource.setDatabaseName("capstone");
                datasource.setUser("root");
                datasource.setPassword("shan@3348");


                conn = datasource.getConnection();
                System.out.println("Connection created successfully. " + conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public Boolean create(Associate associate) {
        boolean status = false;
        try {
            String query = "INSERT INTO associate(name,age,businessunit,email,location, createdtime, updatedtime) values(?,?,?,?,?,?,?)";
            pstmt = getConnection().prepareStatement(query);
            pstmt.setString(1,associate.getName());
            pstmt.setInt(2, associate.getAge());
            pstmt.setString(3, associate.getBusinessUnit());
            pstmt.setString(4, associate.getEmail());
            pstmt.setString(5, associate.getLocation());
            pstmt.setString(6, String.valueOf(associate.getCreateTime()));
            pstmt.setString(7, String.valueOf(associate.getUpdateTime()));

            status = pstmt.executeUpdate() > 0 ? true : false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public List<Associate> getall() {
        List<Associate> accounts = new ArrayList<>();
        try {
            stmt = getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM associate");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String businessUnit = rs.getString("businessunit");
                String email = rs.getString("email");
                String location = rs.getString("location");
                accounts.add(new Associate(id,name, age, businessUnit, email,location));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public boolean update(Associate associate) {
        boolean status = false;
        try {
            String query = "UPDATE associate SET name =?,age=?,businessunit=?,email=?,location=? WHERE id = ?";
            pstmt = getConnection().prepareStatement(query);
            pstmt.setString(1,associate.getName());
            pstmt.setInt(2, associate.getAge());
            pstmt.setString(3, associate.getBusinessUnit());
            pstmt.setString(4, associate.getEmail());
            pstmt.setString(5, associate.getLocation());
            pstmt.setInt(6, associate.getId());

            status = pstmt.executeUpdate() > 0 ? true : false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public boolean delete(int id) {
        boolean status = false;
        try {
            stmt = getConnection().createStatement();

            String query = "DELETE FROM associate WHERE id = " + id;

            status = stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public Associate get(int searchid) {
        Associate associate = null;
        String query = "SELECT * FROM associate WHERE id = " + searchid;
        try {
            stmt = getConnection().createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String businessUnit = rs.getString("businessunit");
                String email = rs.getString("email");
                String location = rs.getString("location");
                associate = new Associate(id,name, age, businessUnit, email,location);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return associate;
    }
}
