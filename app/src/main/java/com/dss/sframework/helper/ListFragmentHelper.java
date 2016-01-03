package com.dss.sframework.helper;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.dss.sframework.R;
import com.dss.sframework.adapter.ListAdapter;

/**
 * Created by gustavo.vieira on 07/05/2015.
 */
public class ListFragmentHelper {

    ListView listItems;

    public void DemoFragment (View view){
        listItems = (ListView) view.findViewById(R.id.listItems);
    }

    public void setAdapter(ListAdapter adapter){
        listItems.setAdapter(adapter);
    }

    public void setListClickListener(AdapterView.OnItemClickListener itemClick){
        listItems.setOnItemClickListener(itemClick);
    }
}
