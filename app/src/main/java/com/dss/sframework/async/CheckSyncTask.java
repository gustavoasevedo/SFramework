package com.dss.sframework.async;

import android.content.Context;
import android.os.AsyncTask;

import com.dss.sframework.constant.ConstantUrl;
import com.dss.sframework.util.HttpUtil;

import java.io.IOException;

/**
 * Created by Marcelo on 11/30/15.
 */
public class CheckSyncTask extends AsyncTask<Void, Void, String> {

    private int mIdUsuario;
    private Context mContext;

    public CheckSyncTask(int idUsuario, Context context) {
        mIdUsuario = idUsuario;
        mContext = context;
    }

    @Override
    protected String doInBackground(Void... params) {

        String url = ConstantUrl.getURL_WEBSERVICE();

        try {
            return HttpUtil.getExecute(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
