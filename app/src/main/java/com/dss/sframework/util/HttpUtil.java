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

import java.io.BufferedReader;
import java.io.BufferedWriter;
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

    public static String postData(String url, ArrayList<NameValuePair> nameValuePairs) {
        // Create a new HttpClient and Post Header
        String result = "";
        InputStream is = null;

        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();


            result = is.toString();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
        return result;
    }

    public static String postJson(String url, String json) {
        // Create a new HttpClient and Post Header
        String result = "";
        InputStream is = null;

        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();
            HttpPost httppostreq = new HttpPost(url);
            StringEntity se = new StringEntity(json.toString());
            se.setContentType("application/json;charset=UTF-8");
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8"));
            httppostreq.setEntity(se);

            HttpResponse httpresponse = httpclient.execute(httppostreq);

            String responseText = null;

            try {
                responseText = EntityUtils.toString(httpresponse.getEntity());
            }catch (ParseException e) {
                e.printStackTrace();
                Log.i("Parse Exception", e + "");
            }

            result = responseText;

            result = is.toString();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
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
