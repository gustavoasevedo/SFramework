package com.dss.sframework.controler.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.dss.sframework.dao.TestObjectDao;
import com.dss.sframework.model.dto.TestObjectList;
import com.dss.sframework.tools.constant.ConstantUrl;
import com.dss.sframework.tools.delegate.UpdateDelegate;
import com.dss.sframework.tools.util.HttpUtil;
import com.google.gson.Gson;

import org.apache.http.NameValuePair;

import java.io.IOException;
import java.util.ArrayList;


public class ListFormatSyncTask extends AsyncTask<Void, Integer, Boolean> {
    private static final String TAG = "UserSyncTask";

    private Exception erro;
    private int totalPorcentagem;
    private UpdateDelegate delegate;
    private int mIdUsuario;
    long time;
//    ProgressDialog progress;

    public ListFormatSyncTask(int idUsuario, UpdateDelegate delegate) {
        this.mIdUsuario = idUsuario;
        this.delegate = delegate;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
//        delegate.getTVTitulo().setText("Carregando Lista de Usuarios");
//        delegate.getTVPorcentagem().setText("Aguarde...");
//        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            progress = new ProgressDialog(delegate.getContext(), android.R.style.Theme_Material_Light_Dialog);
//        }else{
//            progress = new ProgressDialog(delegate.getContext());
//        }
//        progress.setCancelable(false);
//        progress.setTitle("Aguarde...");
//        progress.setMessage("Enviando Coaching");
//        progress.show();

//        time = System.currentTimeMillis();

    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            String result = makeConnection();

            if (result != null && !result.equals("[]") && !result.equals("null")) {

                TestObjectList testObjectList = decompodeJson(result);

                TestObjectDao.getInstance(delegate.getContext()).insertListObject(testObjectList.list);

            } else {
                if (delegate.getContext() != null) {
                    this.erro = new Exception("Erro ao baixar dados de Usuario.");
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            this.erro = new Exception("Erro ao baixar dados de Usuario.");
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

//        long completedIn = System.currentTimeMillis() - time;
//
//        Toast.makeText(delegate.getContext(),String.valueOf(completedIn),Toast.LENGTH_LONG).show();

//        progress.dismiss();

        if (sucesso) {
            delegate.sucessoUpdate(sucesso);
        } else {
            delegate.ErroUpdate(erro);
        }
    }


    public String makeConnection() throws IOException {
        String url = ConstantUrl.URL_WEBSERVICE + ConstantUrl.METHOD_LIST_FORMAT;

        String result = HttpUtil.getExecute(url);

        return result;
    }

    public ArrayList<NameValuePair> buildNameValuePair() {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//        nameValuePairs.add(new BasicNameValuePair("id", ""));

        return nameValuePairs;
    }

    public TestObjectList decompodeJson(String result) {
        Gson serializer = new Gson();
        TestObjectList testObjectList = serializer.fromJson(TestObjectList.setHeaderJson("TestObjectList", result), TestObjectList.class);

        totalPorcentagem = testObjectList.list.size();

        return testObjectList;
    }

}
