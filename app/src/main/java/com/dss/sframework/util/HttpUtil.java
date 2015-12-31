package com.dss.sframework.util;

import android.net.Uri;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

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

    public static String postExecute(String urlString) throws IOException {

        URL url = new URL(urlString);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
        urlConnection.setReadTimeout(DATARETRIEVAL_TIMEOUT);
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Accept", "application/json");
        urlConnection.setDoInput(true);
        urlConnection.connect();

        InputStream is = urlConnection.getInputStream();
        return getStringFromInputStream(is);
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
