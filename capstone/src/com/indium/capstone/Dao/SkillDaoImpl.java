package com.indium.capstone.Dao;

import com.indium.capstone.model.Associate;
import com.indium.capstone.model.Skill;
import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SkillDaoImpl implements SkillDao{
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public SkillDaoImpl() {

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

    public Boolean create(Skill skill) {
        boolean status = false;
        try {
            String query = "INSERT INTO skills(name,description, category, experience, id) values(?,?,?,?,?)";
            pstmt = getConnection().prepareStatement(query);
            pstmt.setString(1,skill.getName());
            pstmt.setString(2, skill.getDescription());
            pstmt.setString(3, skill.getCategory());
            pstmt.setInt(4, skill.getExperience());
            pstmt.setInt(5, skill.getUserId());

            status = pstmt.executeUpdate() > 0 ? true : false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public List<Skill> getall() {
        List<Skill> skills = new ArrayList<>();
        try {
            stmt = getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM skills");

            while (rs.next()) {
                int skillId = rs.getInt("skillid");
//                System.out.println(skillId);
                String name = rs.getString("name");
                String description = rs.getString("description");
                String category = rs.getString("category");
                int experience = rs.getInt("experience");
                int id = rs.getInt("id");
                skills.add(new Skill(skillId,name, description,category,experience,id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skills;
    }

    public boolean update(Skill skill) {
        boolean status = false;
        try {
            String query = "UPDATE skills SET name =?,description=?,category=?,experience =? WHERE skillid= ?";
            pstmt = getConnection().prepareStatement(query);
            pstmt.setString(1,skill.getName());
            pstmt.setString(2,skill.getDescription());
            pstmt.setString(3, skill.getCategory());
            pstmt.setInt(4, skill.getExperience());
            pstmt.setInt(5, skill.getSkillId());

            status = pstmt.executeUpdate() > 0 ? true : false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public boolean delete(int skillID) {
        boolean status = false;
        try {
            stmt = getConnection().createStatement();

            String query = "DELETE FROM skills WHERE skillid = " + skillID;

            status = stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
}
