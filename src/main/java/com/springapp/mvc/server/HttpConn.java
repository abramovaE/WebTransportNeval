package com.springapp.mvc.server;

import org.apache.http.NameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.List;


public class HttpConn {


    public static void setHeaders(HttpURLConnection httpURLConnection){
        httpURLConnection.setRequestProperty("Device-Id", "");
        httpURLConnection.setRequestProperty("Device-OS", "Adnroid 6.0");
        httpURLConnection.setRequestProperty("Version", "2");
        httpURLConnection.setRequestProperty("Accept-Encoding", "gzip");
        httpURLConnection.setRequestProperty("ClientVersion", "1.4.4.4");
    }



    public static String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (NameValuePair pair : params)
        {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }
        return result.toString();
    }
}
