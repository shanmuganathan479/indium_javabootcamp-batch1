package com.indium.capstone.Dao;

import com.indium.capstone.model.Skill;

import java.util.List;

public interface SkillDao {
    public Boolean create(Skill skill);
    public List<Skill> getall();
    public boolean update(Skill skill);
    public boolean delete(int skillID);
    }
