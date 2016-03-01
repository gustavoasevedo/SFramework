package com.dss.sframework.async;

import android.content.Context;
import android.os.AsyncTask;

import com.dss.sframework.constant.ConstantUrl;
import com.dss.sframework.factories.JsonFactory;
import com.dss.sframework.util.HttpUtil;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


public class UserSyncTask extends AsyncTask<Void, Void, String> {

    private int mIdUsuario;
    private Context mContext;

    public UserSyncTask(int idUsuario, Context context) {
        mIdUsuario = idUsuario;
        mContext = context;
    }

    @Override
    protected String doInBackground(Void... params) {

        String url = ConstantUrl.getURL_WEBSERVICE() + ConstantUrl.getMethodUser();

//        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//        nameValuePairs.add(new BasicNameValuePair("id",Integer.toString(mIdUsuario)));


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id",mIdUsuario);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return HttpUtil.postJson(url,jsonObject.toString());

    }


}
