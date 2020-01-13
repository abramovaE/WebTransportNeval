package com.springapp.mvc.service;

import com.springapp.mvc.dao.DepDao;
import com.springapp.mvc.model.Department;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by oem on 23.05.19.
 */
@Service
public class DepServiceImpl implements DepService {

    DepDao depDao;

    public void setDepDao(DepDao depDao) {
        this.depDao = depDao;
    }

    @Override
    @Transactional
    public void saveDep(Department department) {
        this.depDao.saveDep(department);
    }

    @Override
    @Transactional
    public List<Department> getAllDepts() {
        return this.depDao.getAllDepts();
    }

    @Override
    @Transactional
    public void updateDep(Department department) {
        this.depDao.updateDep(department);
    }

    @Override
    @Transactional
    public Department findDepById(long depId) {
        return this.depDao.findDepById(depId);
    }

    @Override
    @Transactional
    public void deleteDep(Department department) {

        this.depDao.deleteDep(department);
    }


}
