package com.springapp.mvc.service;

import com.springapp.mvc.model.Department;

import java.util.List;

/**
 * Created by oem on 23.05.19.
 */
public interface DepService {
    void saveDep(Department department);
    List<Department> getAllDepts();
    void updateDep(Department department);
    Department findDepById(long depId);
    void deleteDep(Department department);
}
