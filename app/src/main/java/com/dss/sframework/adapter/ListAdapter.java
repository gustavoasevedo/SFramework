package com.dss.sframework.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.dss.sframework.R;
import com.dss.sframework.model.TestObject;

import java.util.ArrayList;
import java.util.List;

/**
* Created by gustavo.vieira on 20/01/2015.
*/
public class ListAdapter extends ArrayAdapter<TestObject> {

    private Context context;
    private int layoutResourceId;
    private List<TestObject> lObject;
    private List<TestObject> filteredObject;


    public ListAdapter(Context context, int textViewResourceId,
                       List<TestObject> objects) {
        super(context, textViewResourceId, objects);
        this.setContext(context);
        this.setLayoutResourceId(textViewResourceId);
        this.setlObjects(objects);
        this.setFilteredObject(objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View row = convertView;
        ListClienteHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();

            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ListClienteHolder();

            holder.id = (TextView) row.findViewById(R.id.txtListId);

            holder.nome = (TextView) row.findViewById(R.id.txtListNome);

            holder.data = (TextView) row.findViewById(R.id.txtListData);

            row.setTag(holder);
        } else {
            holder = (ListClienteHolder) row.getTag();
        }

        TestObject testObject = filteredObject.get(position);

        holder.id.setText(String.valueOf(testObject.getId()));

        holder.nome.setText(testObject.getName());

        holder.data.setText(testObject.getDate());

        return row;

    }

    public List<TestObject> getFilteredObject() {
        return filteredObject;
    }

    public void setFilteredObject(List<TestObject> filteredObject) {
        this.filteredObject = filteredObject;
    }

    static class ListClienteHolder {
        TextView id,nome,data;
    }

    @Override
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getLayoutResourceId() {
        return layoutResourceId;
    }

    public void setLayoutResourceId(int layoutResourceId) {
        this.layoutResourceId = layoutResourceId;
    }

    public List<TestObject> getlObjects() {
        return filteredObject;
    }

    public void setlObjects(List<TestObject> lNoticias) {
        this.lObject = lNoticias;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return filteredObject.size();
    }

    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {

                // arrayListNames = (List<String>) results.values;
                filteredObject = (List<TestObject>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                // ArrayList<String> FilteredArrayNames = new
                // ArrayList<String>();
                List<TestObject> FilteredArrayNames = new ArrayList<TestObject>();

                // perform your search here using the searchConstraint String.

                constraint = constraint.toString().toLowerCase();
                for (int i = 0; i < lObject.size(); i++) {
                    TestObject object = lObject.get(i);
                    String dataNames = object.getName();
                    // if
                    // (dataNames.toLowerCase().startsWith(constraint.toString()))
                    // {
                    if (dataNames.toLowerCase().contains(constraint.toString())) {
                        FilteredArrayNames.add(object);
                    }
                }
                // for (int i = 0; i < mDatabaseOfNames.size(); i++) {
                // String dataNames = mDatabaseOfNames.get(i);
                // if
                // (dataNames.toLowerCase().startsWith(constraint.toString())) {
                // FilteredArrayNames.add(dataNames);
                // }
                // }

                results.count = FilteredArrayNames.size();
                results.values = FilteredArrayNames;
                // Log.e("VALUES", results.values.toString());

                return results;
            }
        };

        return filter;
    }

}
