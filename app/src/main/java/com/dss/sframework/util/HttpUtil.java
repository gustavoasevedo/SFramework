package com.dss.sframework.util;

import android.net.Uri;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class HttpUtil {

    private static final int CONNECTION_TIMEOUT = 15000;
    private static final int DATARETRIEVAL_TIMEOUT = 10000;

    public static String getExecute(String urlString) throws IOException {

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
            urlConn = (HttpURLConnection)url.openConnection();
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            urlConn.setUseCaches(false);
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConn.setRequestMethod("POST");
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
            urlConn = (HttpURLConnection)url.openConnection();
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            urlConn.setUseCaches(false);
            urlConn.setRequestProperty("Content-Type", "application/json");

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
