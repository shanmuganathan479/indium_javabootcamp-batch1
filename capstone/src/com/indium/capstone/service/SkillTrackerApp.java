package com.indium.capstone.service;

import com.indium.capstone.Dao.AssociateDao;
import com.indium.capstone.Dao.AssociateDaoJdbcImpl;
import com.indium.capstone.Dao.SkillDao;
import com.indium.capstone.Dao.SkillDaoImpl;
import com.indium.capstone.model.Associate;
import com.indium.capstone.model.Skill;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class SkillTrackerApp implements SkillTracker{
    AssociateDao associateDao;
    SkillDao skillDao;
    private List<Associate> associates = new ArrayList<>();
    private List<Skill> skills = new ArrayList<>();

    public SkillTrackerApp() {

        associateDao = new AssociateDaoJdbcImpl();
        skillDao = new SkillDaoImpl();
    }

    public void addAssociate(Associate associate) {
        boolean createStatus = associateDao.create(associate);
    }

    public void listAssociates() {
        associates = associateDao.getall();
        skills = skillDao.getall();
        System.out.println(skills);
        for (Associate associate : associates) {
            int id = associate.getId();
//            System.out.println(id);
            associate.viewDetails( skills);
            System.out.println();
        }
    }

    public void editAssociate(Associate updatedAssociate) {
        boolean editAssociateStatus = associateDao.update(updatedAssociate);
    }

    public void deleteAssociate(int associateId) {
        boolean deleteAssociateStatus = associateDao.delete(associateId);
    }

    public void addSkillToAssociate(int associateId, Skill skill) {
        boolean addSkillStatus = skillDao.create(skill);
    }

    public void editSkill(int skillId, Skill updatedSkill) {
        boolean editSkillStatus = skillDao.update(updatedSkill);
    }

    public void deleteSkill(int skillId) {
        boolean deleteSkillStatus = skillDao.delete(skillId);
    }

    public void viewAssociate(int associateId){
        Associate associate = associateDao.get(associateId);
        associate.viewDetails();
    }

    public void searchAssociateByName(String name){
        associates = associateDao.getall();
        List<Associate> associatesByName = associates.stream()
                .filter(associate -> associate.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
        for (Associate associate : associatesByName) {
            associate.viewDetails();
        }
    }

    public void searchAssociateByLocation(String location){
        associates = associateDao.getall();
        List<Associate> associatesByLocation = associates.stream()
            .filter(associate -> associate.getLocation().equalsIgnoreCase(location))
            .collect(Collectors.toList());
        for (Associate associate : associatesByLocation) {
            associate.viewDetails();
        }
    }

    public void searchAssociateBySkills(String skillName){
        List<Associate> associatesBySkills =  associates.stream()
                .filter(associate -> associate.getSkills().stream()
                        .anyMatch(skill -> skill.getName().equalsIgnoreCase(skillName)))
                .collect(Collectors.toList());
        for (Associate associate : associatesBySkills) {
            associate.viewDetails();
        }
    }

    public void getTotalAssociates() {
        associates = associateDao.getall();
        System.out.println("the total associates are : "+associates.size());
    }

    public void getTotalAssociatesWithSkillsGreaterThan(int n) {
        skills = skillDao.getall();
        associates = associateDao.getall();
        for (Associate associate : associates) {
            for(Skill skill : skills){
                if(associate.getId()==skill.getUserId()){
                    associate.addSkill(skill);
                }
            }
        }
        int count = (int) associates.stream()
                .filter(associate -> associate.getSkills().size() > n)
                .count();
        System.out.println("total associates with grater than "+n+" skills are : "+count);
    }

    public void getAssociateIdsWithSkillsGreaterThan(int n) {
        skills = skillDao.getall();
        associates = associateDao.getall();
        for (Associate associate : associates) {
            for(Skill skill : skills){
                if(associate.getId()==skill.getUserId()){
                    associate.addSkill(skill);
                }
            }
        }

        List<Integer> associateIdsWithSkillsGreaterThan = associates.stream()
                .filter(associate -> associate.getSkills().size() > n)
                .map(Associate::getId)
                .collect(Collectors.toList());
        System.out.println("associate id's with skills graterthan "+n+" skills are");
        for (int associate : associateIdsWithSkillsGreaterThan) {
            System.out.println(associate);
        }

    }

    public void getTotalAssociatesWithSkills(String requiredSkills) {
        skills = skillDao.getall();
        associates = associateDao.getall();
        for (Associate associate : associates) {
            for(Skill skill : skills){
                if(associate.getId()==skill.getUserId()){
                    associate.addSkill(skill);
                }
            }
        }
        int totalAssociatesWithSkills =  (int) associates.stream()
                .filter(associate -> associate.getSkills().stream()
                        .anyMatch(skill -> skill.getName().equalsIgnoreCase(requiredSkills)))
                .count();
        System.out.println("total associates with given skill are : "+totalAssociatesWithSkills);
    }

    public void importData(){
        int counter =0;
        try(BufferedReader reader = new BufferedReader(new FileReader("./input/input.txt"))){
            String line;
            while((line = reader.readLine())!= null){
                String[] parts = line.split(",");
                String name = parts[0];
                int age = Integer.parseInt(parts[1]);
                String businessUnit = parts[2];
                String email = parts[3];
                String location = parts[4];
                Associate newAssociate = new Associate(name, age, businessUnit, email, location);
                boolean createStatus = associateDao.create(newAssociate);
                counter++;
            }
            System.out.println("imported "+counter+" records");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void exportData(){
        int counter = 0;
        try(PrintWriter out = new PrintWriter(new FileWriter("./output/output.txt"))){
            for(Associate associate : associates){
                StringBuilder accountRecord = new StringBuilder();
                accountRecord.append(associate.getId())
                        .append(",")
                        .append(associate.getName())
                        .append(",")
                        .append(associate.getAge())
                        .append(",")
                        .append(associate.getBusinessUnit())
                        .append(",")
                        .append(associate.getEmail())
                        .append(",")
                        .append(associate.getLocation())
                        .append(",")
                        .append(associate.getCreateTime())
                        .append(",")
                        .append(associate.getUpdateTime())
                        .append("\n");
                out.write(accountRecord.toString());
                counter++;
            }
            System.out.println("exported "+counter+" account details");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


}
