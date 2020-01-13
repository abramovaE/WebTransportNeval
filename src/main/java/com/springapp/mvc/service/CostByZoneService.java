package com.springapp.mvc.service;

import com.springapp.mvc.model.CostByZone;

import java.util.List;

/**
 * Created by kot on 14.09.17.
 */
public interface CostByZoneService {


    List<CostByZone> getAllCosts();
    void addNewZone(CostByZone costByZone);

    CostByZone findZoneById(long costByZoneId);

    void updateCostByZone(CostByZone costByZone);

    Integer findCostByZoneByNumberOfZone(Integer numberOfZone);
}
