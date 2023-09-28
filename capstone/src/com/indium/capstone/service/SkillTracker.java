package com.indium.capstone.service;

import com.indium.capstone.model.Associate;
import com.indium.capstone.model.Skill;

public interface SkillTracker {
    public void listAssociates();
    public void editAssociate(int associateId, Associate updatedAssociate);
    public void deleteAssociate(int associateId);
    public void addSkillToAssociate(int associateId, Skill skill);
    public void editSkill(int skillId, Skill updatedSkill);
    public void deleteSkill(int skillId);
    public void searchAssociateByName(String name);
    public void searchAssociateByLocation(String location);
    public void searchAssociateBySkills(String skillName);
    public void getTotalAssociates();
    public void getTotalAssociatesWithSkillsGreaterThan(int n);
    public void getAssociateIdsWithSkillsGreaterThan(int n);
    public void getTotalAssociatesWithSkills(String requiredSkills);
}
