package com.dss.sframework.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.dss.sframework.R;
import com.dss.sframework.adapter.ListAdapter;
import com.dss.sframework.dao.TestObjectDao;
import com.dss.sframework.helper.ListFragmentHelper;
import com.dss.sframework.model.TestObject;

import java.util.ArrayList;

/**
 * Created by gustavo.vieira on 22/05/2015.
 */
public class ListFragment extends Fragment {

    Context context;
    Activity activity;
    ListFragmentHelper helper;
    View view;
    ListAdapter adapter;
    ArrayList<TestObject> lista;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lista, container, false);

        context = getActivity();
        activity = ((Activity)context);

        helper = new ListFragmentHelper();

        initLayout();
        configureAdapter();

        return view;
    }

    public void initLayout(){
        helper.DemoFragment(view);

    }

    public void configureAdapter(){


        lista = new ArrayList<>();

        lista = TestObjectDao.getInstance(context).selectList();

        adapter = new ListAdapter(context, R.layout.item_list, lista);
        helper.setAdapter(adapter);
        helper.setListClickListener(listClickListener);
        adapter.notifyDataSetChanged();

        helper.addTextChangedListener(textChangedListener);
    }

    public AdapterView.OnItemClickListener listClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> arg0, View v, int position,long arg3) {
            String mensagem = "Nome: " + adapter.getFilteredObject().get(position).getName();
            Toast.makeText(context,mensagem,Toast.LENGTH_SHORT).show();
        }
    };

    public TextWatcher textChangedListener = new TextWatcher(){

        @Override
        public void onTextChanged(CharSequence cs, int arg1, int arg2,
        int arg3) {
            // When user changed the Text
            adapter.getFilter().filter(cs);
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1,
        int arg2, int arg3) {
        }

        @Override
        public void afterTextChanged(Editable arg0) {
        }

    };


}
