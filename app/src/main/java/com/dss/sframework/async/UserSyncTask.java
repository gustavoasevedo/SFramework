package com.dss.sframework.async;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.dss.sframework.constant.ConstantUrl;
import com.dss.sframework.dao.TestTable;
import com.dss.sframework.dto.TestObjectList;
import com.dss.sframework.factories.JsonFactory;
import com.dss.sframework.util.HttpUtil;
import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


public class UserSyncTask extends AsyncTask<Void, Void, String> {

    private int mIdUsuario;
    private Context mContext;
    private TestTable testTable;

    public UserSyncTask(int idUsuario, Context context) {
        mIdUsuario = idUsuario;
        mContext = context;
        testTable = new TestTable(mContext);
    }

    @Override
    protected String doInBackground(Void... params) {

        String url = ConstantUrl.getURL_WEBSERVICE() + ConstantUrl.getMethodUser();

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("id",Integer.toString(mIdUsuario)));


        return HttpUtil.postData(url,nameValuePairs);

    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        if (result != null && !result.equals("[]") && !result.equals("null")){
            Gson serializer = new Gson();
            TestObjectList testObjectList = serializer.fromJson(TestObjectList.setHeaderJson("TestObjectList", result), TestObjectList.class);

            testTable.insert( testObjectList.list);
        } else {
            if (mContext != null) {
                Toast.makeText(mContext, "Erro ao baixar dados", Toast.LENGTH_LONG).show();
            }
        }
    }


}
