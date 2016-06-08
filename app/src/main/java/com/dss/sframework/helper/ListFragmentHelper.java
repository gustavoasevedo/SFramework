package com.dss.sframework.helper;

import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.dss.sframework.R;
import com.dss.sframework.adapter.ListAdapter;

/**
 * Created by gustavo.vieira on 07/05/2015.
 */
public class ListFragmentHelper {

    ListView listItems;
    EditText txtSearch;
    SwipeRefreshLayout swipeContainer;

    public void ListFragment (View view){
        listItems = (ListView) view.findViewById(R.id.listItems);
        txtSearch = (EditText) view.findViewById(R.id.txtSearch);
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);


        swipeContainer.setColorSchemeColors(
                view.getContext().getResources().getColor(R.color.blue),
                view.getContext().getResources().getColor(R.color.lightblue),
                view.getContext().getResources().getColor(R.color.blue),
                view.getContext().getResources().getColor(R.color.white)
        );
    }

    public void setAdapter(ListAdapter adapter){
        listItems.setAdapter(adapter);
    }

    public void setListClickListener(AdapterView.OnItemClickListener itemClick){
        listItems.setOnItemClickListener(itemClick);
    }

    public void addTextChangedListener(TextWatcher textWatcher){
        txtSearch.addTextChangedListener(textWatcher);
    }

    public void setSwipeListener(SwipeRefreshLayout.OnRefreshListener onRefreshListener){
        swipeContainer.setOnRefreshListener(onRefreshListener);
    }


    public void finishSwipe(){
        swipeContainer.setRefreshing(false);
    }
}
