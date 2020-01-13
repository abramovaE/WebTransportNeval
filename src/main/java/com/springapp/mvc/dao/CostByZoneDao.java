package com.springapp.mvc.dao;

import com.springapp.mvc.model.CostByZone;

import java.util.List;

/**
 * Created by kot on 14.09.17.
 */
public interface CostByZoneDao {

    List<CostByZone> getAllCosts();

    void addNewZone(CostByZone costByZone);

    CostByZone findZoneById(long costByZoneId);

    void updateCostByZome(CostByZone costByZone);

    Integer findCostByZoneByNumberOfZone(Integer numberOfZone);
}
