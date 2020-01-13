package com.springapp.mvc.service;

//import com.springapp.mvc.dao.CheckDao;

import com.springapp.mvc.dao.CheckDao;
import com.springapp.mvc.fns.*;

import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import org.apache.http.client.HttpClient;

import org.apache.http.impl.client.HttpClients;

import org.springframework.transaction.annotation.Transactional;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by oem on 21.06.18.
 */

@Service
public class EmailService {




    CheckDao checkDao;
//

    public void setCheckDao(CheckDao checkDao) {
        this.checkDao = checkDao;
    }

//    private static String qr = "t=20180628T212900&enums=5599.00&fn=8710000101576210&i=7939&fp=2258109801&n=1";
//    String test2 = "t=20180728T1120&enums=243.50&fn=8710000101887065&i=111707&fp=3598877878&n=1";
//
    String detmir ="t=20181125T1215&enums=806.50&fn=8710000101397900&i=15811&fp=4090489490&n=1";
//
//    private static HttpClient httpclient = HttpClients.createMinimal();
//    private static String login = "+79052066960";
//    private static String password = "871202";

//@Scheduled(fixedDelay = 50000)
@Transactional
public void print(){


    try {
        FnsCheck fnsCheck = FNS.getCheck(detmir, "+79052066960", "934383");
        System.out.println(fnsCheck.getDocument().getReceipt().getDateTime());
    } catch (CheckNotFoundException e) {
        e.printStackTrace();
    } catch (InternalFnsException e) {
        e.printStackTrace();
    } catch (UserDoesNotExistException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }


//        String url_save_jsonCheck = "https://transport.nevalink.net/phpinfo.php";
//
//    URL u;
//    try {
//        u = new URL(url_save_jsonCheck);
//
//       JSONObject jsonCheck = new JSONObject();
//        jsonCheck.put("qr_code", "qrrrrrrrrrrr");
//        HttpURLConnection httpsURLConnection = (HttpURLConnection) u.openConnection();
//        httpsURLConnection.setRequestMethod("POST");
//        httpsURLConnection.setRequestProperty("Content-Type", "application/json");
//        httpsURLConnection.setDoInput(true);
//        httpsURLConnection.setDoOutput(true);
//
//        DataOutputStream dataOutputStream = new DataOutputStream(httpsURLConnection.getOutputStream());
//        dataOutputStream.writeBytes(jsonCheck.toString());
//        dataOutputStream.flush();
//        dataOutputStream.close();
//
//        httpsURLConnection.connect();
//        int response = httpsURLConnection.getResponseCode();
//        System.out.println(response);
//
//    } catch (MalformedURLException e) {
//        e.printStackTrace();
//    } catch (ProtocolException e) {
//        e.printStackTrace();
//    } catch (IOException e) {
//        e.printStackTrace();
//    }



}

}
