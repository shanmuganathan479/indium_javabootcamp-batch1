package com.indium.capstone.model;

import com.indium.capstone.Dao.AssociateDaoJdbcImpl;
import com.indium.capstone.Dao.SkillDao;
import com.indium.capstone.Dao.SkillDaoImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Associate {
    private static int nextId = 1;
    private int id;
    private String name;
    private int age;
    private String businessUnit;
    private String email;
    private String location;
    public List<Skill> skills = new ArrayList<>();
    private Date createTime;
    private Date updateTime;
    SkillDao skillDao;

    public Associate() {
        skillDao = new SkillDaoImpl();
    }

    public Associate(String name, int age, String businessUnit, String email, String location) {
        this.id = nextId++;
        this.name = name;
        this.age = age;
        this.businessUnit = businessUnit;
        this.email = email;
        this.location = location;
        this.createTime = new Date();
        this.updateTime = new Date();
    }

    public Associate(int id,String name, int age, String businessUnit, String email, String location) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.businessUnit = businessUnit;
        this.email = email;
        this.location = location;
        this.createTime = new Date();
        this.updateTime = new Date();
    }

    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    public void editSkill(int skillId, Skill updatedSkill) {
        for (Skill skill : skills) {
            if (skill.getId() == skillId) {
                skill.setName(updatedSkill.getName());
                skill.setDescription(updatedSkill.getDescription());
                skill.setCategory(updatedSkill.getCategory());
                skill.setExperience(updatedSkill.getExperience());
                updateTime = new Date();
                break;
            }
        }
    }

    public void deleteSkill(int skillId) {
        skills.removeIf(skill -> skill.getId() == skillId);
        updateTime = new Date();
    }

    public void viewDetails(List<Skill> allSkills) {
//        List<Skill> allskills;
//        allskills = skillDao.getall();
//        System.out.println(skills);
        System.out.println("Associate ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Business Unit: " + businessUnit);
        System.out.println("Email: " + email);
        System.out.println("Location: " + location);
        System.out.println("Skills: ");
        for (Skill skill : allSkills) {
            if(skill.getUserId()==id){
//                System.out.println(skill.getUserId());
                System.out.println("  Skill ID: " + skill.getSkillId() + ", Name: " + skill.getName());
            }
        }
    }

    public void viewDetails() {
//        skills = skillDao.getall();
        System.out.println("Associate ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Business Unit: " + businessUnit);
        System.out.println("Email: " + email);
        System.out.println("Location: " + location);
        System.out.println("Skills: ");
        for (Skill skill : skills) {
//            if(skill.getId()==id){
                System.out.println("  Skill ID: " + skill.getId() + ", Name: " + skill.getName());
//            }
        }
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Associate.nextId = nextId;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
