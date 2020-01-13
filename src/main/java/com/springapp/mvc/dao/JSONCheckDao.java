package com.springapp.mvc.dao;

import com.springapp.mvc.model.checks.JSONCheck;

import java.util.List;

/**
 * Created by oem on 24.12.18.
 */
public interface JSONCheckDao {

    List<JSONCheck> getAllJSONChecks();

}
