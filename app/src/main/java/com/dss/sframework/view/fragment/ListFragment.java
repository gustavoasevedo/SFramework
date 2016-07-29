package com.dss.sframework.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.dss.sframework.R;
import com.dss.sframework.controler.tasks.UserSyncTask;
import com.dss.sframework.model.dto.TestObjectList;
import com.dss.sframework.model.entity.TestObject;
import com.dss.sframework.tools.delegate.UpdateDelegate;
import com.dss.sframework.view.adapter.ListAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_lista)
public class ListFragment extends Fragment implements UpdateDelegate{

    @ViewById
    ListView listItems;

    @ViewById
    EditText txtSearch;

    @ViewById
    SwipeRefreshLayout swipeContainer;

    Context context;

    Activity activity;

    @Bean
    ListAdapter adapter;

    @Bean(TestObjectList.class)
    TestObjectList lista;


    @AfterViews
    void afterViews() {
        context = getActivity();
        activity = ((Activity)context);

        configureSwipe();

        configureAdapter();
    }

    public void configureSwipe(){
        swipeContainer.setColorSchemeColors(
                getContext().getResources().getColor(R.color.blue),
                getContext().getResources().getColor(R.color.lightblue),
                getContext().getResources().getColor(R.color.blue),
                getContext().getResources().getColor(R.color.white)
        );

    }

    public void configureAdapter(){
        
        listItems.setAdapter(adapter);

        listItems.setOnItemClickListener(listClickListener);
        adapter.notifyDataSetChanged();

        txtSearch.addTextChangedListener(textChangedListener);

        swipeContainer.setOnRefreshListener(onRefreshListener);
    }

    @ItemClick
    void listItemsItemClicked(TestObject testObject) {
        String mensagem = "Nome: " + testObject.getName();
        Toast.makeText(context,mensagem,Toast.LENGTH_SHORT).show();
    }

    public AdapterView.OnItemClickListener listClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> arg0, View v, int position,long arg3) {

        }
    };


    public SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {

            UserSyncTask userSyncTask = new UserSyncTask(1,ListFragment.this);
            userSyncTask.execute();

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


    @Override
    public void sucessoUpdate(boolean sucesso) {
        swipeContainer.setRefreshing(false);

        listItems.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void ErroUpdate(Exception e) {
        swipeContainer.setRefreshing(false);
    }

}
