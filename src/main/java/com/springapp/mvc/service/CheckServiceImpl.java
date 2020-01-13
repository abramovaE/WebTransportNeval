package com.springapp.mvc.service;

import com.springapp.mvc.dao.CheckDao;
import com.springapp.mvc.fns.*;
import com.springapp.mvc.model.Check;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * Created by oem on 24.11.18.
 */

@Service
public class CheckServiceImpl implements CheckService {

    private CheckDao checkDao;

//    String detmir ="t=20181125T1215&enums=806.50&fn=8710000101397900&i=15811&fp=4090489490&n=1";


    public void setCheckDao(CheckDao checkDao) {
        this.checkDao = checkDao;
    }

    @Override
    @Transactional
    public void saveCheck(Check check) {
        this.checkDao.saveCheck(check);
    }



//    private static HttpClient httpclient = HttpClients.createMinimal();
//    private static String login = "+79052066960";
//    private static String password = "871202";

////    @Scheduled(fixedDelay=50000)
//    @Override
//    @Transactional
//    public void generateValue() {
////        Check check = new Check();
////        check.setValue(11);
////        checkDao.saveCheck(check);
//
//    }
}
