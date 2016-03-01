package com.dss.sframework.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.dss.sframework.R;
import com.dss.sframework.adapter.ListAdapter;
import com.dss.sframework.async.UserSyncTask;
import com.dss.sframework.dao.TestTable;
import com.dss.sframework.factories.JsonFactory;
import com.dss.sframework.helper.ListFragmentHelper;
import com.dss.sframework.model.TestObject;
import com.dss.sframework.dto.TestObjectList;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavo.vieira on 22/05/2015.
 */
public class ListFragment extends Fragment {

    Context context;
    Activity activity;
    ListFragmentHelper helper;
    View view;
    ListAdapter adapter;
    List<TestObject> lista;
    TestTable testTable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lista, container, false);

        context = getActivity();
        activity = ((Activity)context);

        helper = new ListFragmentHelper();

        initLayout();

        testTable = new TestTable(context);

        ResultadoAtualizaTask(4);

        return view;
    }

    public void initLayout(){
        helper.DemoFragment(view);

    }

    public void configureAdapter(){
        lista = testTable.selectList();
        adapter = new ListAdapter(context, R.layout.item_list, lista);
        helper.setAdapter(adapter);
        helper.setListClickListener(listClickListener);
        adapter.notifyDataSetChanged();
    }

    public AdapterView.OnItemClickListener listClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> arg0, View v, int position,long arg3) {
            String mensagem = "Nome: " + lista.get(position).getName();
            Toast.makeText(context,mensagem,Toast.LENGTH_SHORT).show();
        }
    };


    private void ResultadoAtualizaTask(int idUsuario) {
        new UserSyncTask(idUsuario,context) {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                if (result != null && !result.equals("[]") && !result.equals("null")){
                    Gson serializer = new Gson();
                    TestObjectList testObjectList = serializer.fromJson(result.toString(),TestObjectList.class);

                    for(TestObject object : testObjectList.list) {
                        testTable.insert(object);
                    }
                    configureAdapter();
                } else {
                    if (context != null) {
                        Toast.makeText(context, "Erro ao baixar dados", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }.execute();
    }


}
