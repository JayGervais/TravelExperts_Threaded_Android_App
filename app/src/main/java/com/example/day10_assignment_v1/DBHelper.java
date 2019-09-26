package com.example.day10_assignment_v1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DBHelper
{
    // global strings for API
    public static String apiURL()
    {
        String url = "infastory.com";
        return "https://" + url;
    }

    public static String apiAuth()
    {
        String url = "infastory.com";
        return url;
    }

    // input stream used in multiple queries
    public static String urlInputStream(String urlWebService)
    {
        try
        {
            URL url = new URL(urlWebService);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            StringBuilder sb = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String json;
            while ((json = bufferedReader.readLine()) != null)
            {
                sb.append(json + "\n");
            }
            return sb.toString().trim();
        } catch (Exception e)
        {
            return null;
        }
    }
}
