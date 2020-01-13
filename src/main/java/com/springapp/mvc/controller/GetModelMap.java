package com.springapp.mvc.controller;

import com.springapp.mvc.model.Report;
import org.springframework.ui.Model;

import java.util.Map;

/**
 * Created by oem on 14.06.19.
 */
public interface GetModelMap {

    void getModelMap(Model model, Report report);


}
