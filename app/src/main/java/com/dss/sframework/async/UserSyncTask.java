package com.dss.sframework.async;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.dss.sframework.constant.ConstantUrl;
import com.dss.sframework.dao.TestObjectDao;
import com.dss.sframework.delegate.UpdateDelegate;
import com.dss.sframework.dto.TestObjectList;
import com.dss.sframework.util.HttpUtil;
import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;


public class UserSyncTask extends AsyncTask<Void, Integer, Boolean> {
    private static final String TAG = "UserSyncTask";

    private Exception erro;
    private int totalPorcentagem;
    private UpdateDelegate delegate;

    private int mIdUsuario;

    public UserSyncTask(int idUsuario,UpdateDelegate delegate) {
        this.mIdUsuario = idUsuario;
        this.delegate = delegate;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
//        delegate.getTVTitulo().setText("Carregando Lista de Usuarios");
//        delegate.getTVPorcentagem().setText("Aguarde...");
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            String url = ConstantUrl.getURL_WEBSERVICE() + ConstantUrl.getMethodUser();

            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("id", Integer.toString(mIdUsuario)));

            String result = HttpUtil.postData(url, nameValuePairs);

            if (result != null && !result.equals("[]") && !result.equals("null")) {
                Gson serializer = new Gson();
                TestObjectList testObjectList = serializer.fromJson(TestObjectList.setHeaderJson("TestObjectList", result), TestObjectList.class);

                totalPorcentagem = testObjectList.list.size();

                TestObjectDao.getInstance(delegate.getContext()).insertListObject(testObjectList.list);
            } else {
                if (delegate.getContext() != null) {
                    Toast.makeText(delegate.getContext(), "Erro ao baixar dados de Usuario", Toast.LENGTH_LONG).show();
                }
            }

            return true;
        }catch (Exception e){
            this.erro = e;
            return false;
        }

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
//        delegate.getTVPorcentagem().setText(((values[0] * 100) / totalPorcentagem) + " %");
    }

    @Override
    protected void onPostExecute(Boolean sucesso) {
        Log.i(TAG, "Sucesso: " + sucesso);

        if (sucesso) {
            delegate.sucessoUpdate(sucesso);
        } else {
            delegate.ErroUpdate(erro);
        }
    }

}
