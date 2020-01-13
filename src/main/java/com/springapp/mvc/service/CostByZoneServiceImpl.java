package com.springapp.mvc.service;

import com.springapp.mvc.dao.CostByZoneDao;
import com.springapp.mvc.model.CostByZone;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * Created by kot on 14.09.17.
 */

@Service
public class CostByZoneServiceImpl implements CostByZoneService {

    private CostByZoneDao costByZoneDao;

    public void setCostByZoneDao(CostByZoneDao costByZoneDao) {
        this.costByZoneDao = costByZoneDao;
    }

    @Override
    @Transactional
    public List<CostByZone> getAllCosts() {
        return this.costByZoneDao.getAllCosts();
    }

    @Override
    @Transactional
    public void addNewZone(CostByZone costByZone) {
        this.costByZoneDao.addNewZone(costByZone);

    }

    @Override
    @Transactional
    public CostByZone findZoneById(long costByZoneId) {
        return this.costByZoneDao.findZoneById(costByZoneId);
    }

    @Override
    @Transactional
    public void updateCostByZone(CostByZone costByZone) {
        this.costByZoneDao.updateCostByZome(costByZone);
    }

    @Override
    @Transactional
    public Integer findCostByZoneByNumberOfZone(Integer numberOfZone) {
        return this.costByZoneDao.findCostByZoneByNumberOfZone(numberOfZone);
    }
}
