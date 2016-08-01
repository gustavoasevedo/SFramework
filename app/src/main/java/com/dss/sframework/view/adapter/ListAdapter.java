package com.dss.sframework.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;

import com.dss.sframework.dao.TestObjectDao;
import com.dss.sframework.model.dto.TestObjectDTO;
import com.dss.sframework.model.dto.TestObjectList;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

//import com.dss.sframework.view.fragment.ListFragment_;

@EBean
public class ListAdapter extends BaseAdapter {

    @Bean(TestObjectList.class)
    TestObjectList testObjectList;

    @RootContext
    Context context;

    private List<TestObjectDTO> lObject;
    private List<TestObjectDTO> filteredObject;

    @AfterInject
    void initAdapter() {
        filteredObject = TestObjectDao.getInstance(context).selectList();
        lObject = TestObjectDao.getInstance(context).selectList();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        ListItemView listItemView;

        if (convertView == null) {

            listItemView = ListItemView_.build(context);
        } else {
            listItemView = (ListItemView) convertView;
        }

        listItemView.bind(getItem(position));

        return listItemView;

    }

    public List<TestObjectDTO> getFilteredObject() {
        return filteredObject;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<TestObjectDTO> getlObjects() {
        return filteredObject;
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return filteredObject.size();
    }

    @Override
    public TestObjectDTO getItem(int position) {
        return filteredObject.get(position);
    }


    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    public Filter getFilter() {

        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {

                // arrayListNames = (List<String>) results.values;
                filteredObject = (List<TestObjectDTO>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                // ArrayList<String> FilteredArrayNames = new
                // ArrayList<String>();
                List<TestObjectDTO> FilteredArrayNames = new ArrayList<TestObjectDTO>();

                // perform your search here using the searchConstraint String.

                constraint = constraint.toString().toLowerCase();
                for (int i = 0; i < lObject.size(); i++) {
                    TestObjectDTO object = lObject.get(i);
                    String dataNames = object.getName();
                    if (dataNames.toLowerCase().contains(constraint.toString())) {
                        FilteredArrayNames.add(object);
                    }
                }

                results.count = FilteredArrayNames.size();
                results.values = FilteredArrayNames;

                return results;
            }
        };

        return filter;
    }

}
