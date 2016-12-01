package com.nextech.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by welcome on 11/25/2016.
 */
public class NetClientGetRally {
    public String netClientGetRaly(String requerdData){

        try {

            URL url = new URL(requerdData);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            StringBuffer stringBuffer = new StringBuffer("");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                stringBuffer.append(output);
            }

            conn.disconnect();
            return stringBuffer.toString();
        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return null;
    }
}

