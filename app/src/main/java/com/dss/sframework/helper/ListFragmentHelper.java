package com.dss.sframework.helper;

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

    public void DemoFragment (View view){
        listItems = (ListView) view.findViewById(R.id.listItems);
        txtSearch = (EditText) view.findViewById(R.id.txtSearch);
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
}
