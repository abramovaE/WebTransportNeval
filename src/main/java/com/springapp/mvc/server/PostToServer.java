package com.springapp.mvc.server;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostToServer {
    private int resp;
    private String url;
    private HashMap<String, String> params;

    public PostToServer(String url, int resp, HashMap<String, String> params) {
        this.resp = resp;
        this.url = url;
        if(params != null) {
            this.params = params;
        }
        else {
            this.params = new HashMap<>();
        }
    }




    public String post() {
        URL u;
        String res = null;
        try
        {
            u = new URL(url);
            HttpURLConnection httpsURLConnection = (HttpURLConnection) u.openConnection();
            httpsURLConnection.setRequestMethod("POST");
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setReadTimeout(7000);
            httpsURLConnection.setConnectTimeout(10000);

            List<NameValuePair> params = new ArrayList<>();
            for (Map.Entry<String, String> pair: this.params.entrySet()){
                params.add(new BasicNameValuePair(pair.getKey(), pair.getValue()));
            }

            OutputStream os = httpsURLConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(HttpConn.getQuery(params));
            writer.flush();
            writer.close();
            os.close();

            httpsURLConnection.connect();
            BufferedReader br;
            StringBuilder content;
            InputStreamReader reader = new InputStreamReader(httpsURLConnection.getInputStream());
            br = new BufferedReader(reader);
            content = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line);
            }
            res = content.toString();
            reader.close();

        } catch (
                MalformedURLException e)
        {
            e.printStackTrace();
        } catch (
                ProtocolException e)

        {
            e.printStackTrace();
        } catch (
                IOException e) {

        }

        return res;
    }
}
