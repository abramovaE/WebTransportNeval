package com.springapp.mvc.service;

import com.springapp.mvc.dao.JSONCheckDao;
import com.springapp.mvc.model.checks.JSONCheck;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by oem on 24.12.18.
 */
@Service
public class JSONCheckServiceImpl implements JSONCheckService {

    private JSONCheckDao jsonCheckDao;

    public void setJsonCheckDao(JSONCheckDao jsonCheckDao) {
        this.jsonCheckDao = jsonCheckDao;
    }

    @Override
    @Transactional
    public List<JSONCheck> getAllJSONChecks() {
        return this.jsonCheckDao.getAllJSONChecks();
    }



//        @Scheduled(fixedDelay = 10000)
        @Transactional
        public void saveCheck(){

            List<JSONCheck> jsonChecks = getAllJSONChecks();




        }



}
