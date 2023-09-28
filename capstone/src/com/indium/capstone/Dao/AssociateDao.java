package com.indium.capstone.Dao;

import com.indium.capstone.model.Associate;

import java.util.List;

public interface AssociateDao {

    public Boolean create(Associate associate);
    public List<Associate> getall();
    public boolean update(Associate associate);
    public boolean delete(int id);
    public Associate get(int searchid);
    }
