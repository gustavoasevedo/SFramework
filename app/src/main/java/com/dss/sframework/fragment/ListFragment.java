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
import com.dss.sframework.factories.JsonFactory;
import com.dss.sframework.helper.ListFragmentHelper;
import com.dss.sframework.objects.TestObject;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lista, container, false);

        context = getActivity();
        activity = ((Activity)context);

        helper = new ListFragmentHelper();

        initLayout();

        loadList();

        return view;
    }

    public void initLayout(){
        helper.DemoFragment(view);

    }

    public void loadList(){

        TestObject testObject = new TestObject(4,"nome","04-05-2015");
        TestObject testObject2 = new TestObject(5,"nome do ze","05-05-2015");
        TestObject testObject3 = new TestObject(6,"nome do ze antonho","06-05-2015");

        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(testObject);
        arrayList.add(testObject2);
        arrayList.add(testObject3);

        JSONObject json= new JSONObject();

        try {
            JSONArray jsonArray = new JSONArray();
            for(Object i:arrayList){
                jsonArray.put(JsonFactory.getJsonObject(i));
            }

            json.put("TestObjectList",jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Desserialize a list
        Gson serializer = new Gson();
        TestObjectList testObjectList = serializer.fromJson(json.toString(),TestObjectList.class);
        lista = testObjectList.list;
        configureAdapter();

    }

    public void configureAdapter(){
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

}
