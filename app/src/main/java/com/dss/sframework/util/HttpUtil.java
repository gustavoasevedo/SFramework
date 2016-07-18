package com.dss.sframework.util;

import android.util.Log;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class HttpUtil {

    private static final int CONNECTION_TIMEOUT = 15000;
    private static final int DATARETRIEVAL_TIMEOUT = 10000;

    private static final String TAG = "HTTPUTIL";

    public static String getExecute(String urlString) throws IOException {

        Log.i(TAG,"Efeteuando Get para: " + urlString);

        URL url = new URL(urlString);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
        urlConnection.setReadTimeout(DATARETRIEVAL_TIMEOUT);
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("Accept", "application/json");
        urlConnection.setDoInput(true);
        urlConnection.connect();

        InputStream is = urlConnection.getInputStream();
        return getStringFromInputStream(is);
    }


    public static String postData(String urlString, ArrayList<NameValuePair> nameValuePairs) {
        URL url = null;
        HttpURLConnection urlConn = null;
        DataOutputStream printout;
        DataInputStream input;
        try {
            url = new URL(urlString);
        }
        catch (Exception ex){

        }
        String result = "";
        try
        {
            Log.i(TAG,"Efeteuando post para: " + urlString);

            Log.i(TAG,"Com os Dados: " + nameValuePairs.toString());


            urlConn = (HttpURLConnection)url.openConnection();
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            urlConn.setUseCaches(false);
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConn.setRequestMethod("POST");
            urlConn.setConnectTimeout(CONNECTION_TIMEOUT);
            urlConn.setReadTimeout(DATARETRIEVAL_TIMEOUT);
            urlConn.connect();

            // Send POST output.
            printout = new DataOutputStream(urlConn.getOutputStream ());

            for(NameValuePair nameValuePair : nameValuePairs){
                String parameter = nameValuePair.getName() + "=" + nameValuePair.getValue();
                printout.write(parameter.getBytes("UTF-8"));
            }

            printout.flush();
            printout.close();

            InputStreamReader _is;
            if (urlConn.getResponseCode() / 100 == 2)
                _is = new InputStreamReader(urlConn.getInputStream());
            else
                _is = new InputStreamReader(urlConn.getErrorStream());

            BufferedReader reader = new BufferedReader(_is);
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                // Append server response in string
                sb.append(line + "\n");
            }


            result = sb.toString();
        }
        catch (Exception ex){

        }


        return result;
    }

    public static String postJson(String urlString, JSONObject json) {
        URL url = null;
        HttpURLConnection urlConn = null;
        DataOutputStream printout;
        DataInputStream input;
        try {
            url = new URL(urlString);
        }
        catch (Exception ex){

        }
        String result = "";
        try
        {
            Log.i(TAG,"Efeteuando post para: " + urlString);

            Log.i(TAG,"Com os Dados: " + json.toString());

            urlConn = (HttpURLConnection)url.openConnection();
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            urlConn.setUseCaches(false);
            urlConn.setRequestProperty("Content-Type", "application/json");
            urlConn.setConnectTimeout(CONNECTION_TIMEOUT);
            urlConn.setReadTimeout(DATARETRIEVAL_TIMEOUT);
            urlConn.connect();

            // Send POST output.
            printout = new DataOutputStream(urlConn.getOutputStream ());
            String jsonString = json.toString();
            printout.write(jsonString.getBytes("UTF-8"));
            printout.flush();
            printout.close();

            InputStreamReader _is;
            if (urlConn.getResponseCode() / 100 == 2)
                _is = new InputStreamReader(urlConn.getInputStream());
            else
                _is = new InputStreamReader(urlConn.getErrorStream());

            BufferedReader reader = new BufferedReader(_is);
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                // Append server response in string
                sb.append(line + "\n");
            }


            result = sb.toString();
        }
        catch (Exception ex){

        }


        return result;
    }

    public static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }
}
