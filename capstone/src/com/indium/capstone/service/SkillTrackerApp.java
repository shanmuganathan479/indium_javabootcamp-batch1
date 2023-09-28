package com.indium.capstone.service;

import com.indium.capstone.model.Associate;
import com.indium.capstone.model.Skill;

import java.util.*;
import java.util.stream.Collectors;

public class SkillTrackerApp implements SkillTracker{
    private List<Associate> associates = new ArrayList<>();
    private List<Skill> skills = new ArrayList<>();

    public void addAssociate(Associate associate) {
        associates.add(associate);
    }

    public void listAssociates() {
        for (Associate associate : associates) {
            associate.viewDetails();
            System.out.println();
        }
    }

    public void editAssociate(int associateId, Associate updatedAssociate) {
        for (Associate associate : associates) {
            if (associate.getId() == associateId) {
                associate.setName(updatedAssociate.getName());
                associate.setAge(updatedAssociate.getAge());
                associate.setBusinessUnit(updatedAssociate.getBusinessUnit());
                associate.setEmail(updatedAssociate.getEmail());
                associate.setLocation(updatedAssociate.getLocation());
                associate.setUpdateTime(new Date());
                break;
            }
        }
    }

    public void deleteAssociate(int associateId) {
        associates.removeIf(associate -> associate.getId() == associateId);
    }

    public void addSkillToAssociate(int associateId, Skill skill) {
        for (Associate associate : associates) {
            if (associate.getId() == associateId) {
                associate.addSkill(skill);
                break;
            }
        }
    }

    public void editSkill(int skillId, Skill updatedSkill) {
        for (Skill skill : skills) {
            if (skill.getId() == skillId) {
                skill.setName(updatedSkill.getName());
                skill.setDescription(updatedSkill.getDescription());
                skill.setCategory(updatedSkill.getCategory());
                skill.setExperience(updatedSkill.getExperience());
                break;
            }
        }
        for (Associate associate : associates) {
            associate.editSkill(skillId, updatedSkill);
        }
    }

    public void deleteSkill(int skillId) {
        skills.removeIf(skill -> skill.getId() == skillId);
        for (Associate associate : associates) {
            associate.deleteSkill(skillId);
        }
    }

    public void viewAssociate(int associateId){
        for (Associate associate : associates) {
            if (associate.getId() == associateId) {
                associate.viewDetails();
            }
        }
    }

    public void searchAssociateByName(String name){
        List<Associate> associatesByName = associates.stream()
                .filter(associate -> associate.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
        for (Associate associate : associatesByName) {
            associate.viewDetails();
        }
    }

    public void searchAssociateByLocation(String location){
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
        System.out.println("the total associates are : "+associates.size());
    }

    public void getTotalAssociatesWithSkillsGreaterThan(int n) {
        int count = (int) associates.stream()
                .filter(associate -> associate.getSkills().size() > n)
                .count();
        System.out.println("total associates with grater than "+n+" skills are : "+count);
    }

    public void getAssociateIdsWithSkillsGreaterThan(int n) {
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
        int totalAssociatesWithSkills =  (int) associates.stream()
                .filter(associate -> associate.getSkills().stream()
                        .anyMatch(skill -> skill.getName().equalsIgnoreCase(requiredSkills)))
                .count();
        System.out.println("total associates with given skill are : "+totalAssociatesWithSkills);
    }


}
