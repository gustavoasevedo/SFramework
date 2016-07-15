package com.dss.sframework.fragment;

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
import com.dss.sframework.adapter.ListAdapter;
import com.dss.sframework.dao.TestObjectDao;
import com.dss.sframework.delegate.UpdateDelegate;
import com.dss.sframework.model.TestObject;
import com.dss.sframework.tasks.UserSyncTask;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by gustavo.vieira on 22/05/2015.
 */
@EFragment(R.layout.fragment_lista)
public class ListFragment extends Fragment implements UpdateDelegate {

    @ViewById
    ListView listItems;

    @ViewById
    EditText txtSearch;

    @ViewById
    SwipeRefreshLayout swipeContainer;


    Context context;
    Activity activity;
    ListAdapter adapter;
    ArrayList<TestObject> lista;


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


        lista = new ArrayList<>();

        lista = TestObjectDao.getInstance(context).selectList();

        adapter = new ListAdapter(context, R.layout.item_list, lista);
        listItems.setAdapter(adapter);

        listItems.setOnItemClickListener(listClickListener);
        adapter.notifyDataSetChanged();

        txtSearch.addTextChangedListener(textChangedListener);

        swipeContainer.setOnRefreshListener(onRefreshListener);
    }

    public AdapterView.OnItemClickListener listClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> arg0, View v, int position,long arg3) {
            String mensagem = "Nome: " + adapter.getFilteredObject().get(position).getName();
            Toast.makeText(context,mensagem,Toast.LENGTH_SHORT).show();
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
        lista = TestObjectDao.getInstance(context).selectList();

        adapter = new ListAdapter(context, R.layout.item_list, lista);
        listItems.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void ErroUpdate(Exception e) {
        swipeContainer.setRefreshing(false);
    }
}
