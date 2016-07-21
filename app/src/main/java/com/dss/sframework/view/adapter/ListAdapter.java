package com.dss.sframework.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.dss.sframework.R;
import com.dss.sframework.model.dto.TestObjectDTO;

import java.util.ArrayList;
import java.util.List;

/**
* Created by gustavo.vieira on 20/01/2015.
*/
public class ListAdapter extends ArrayAdapter<TestObjectDTO> {

    private Context context;
    private int layoutResourceId;
    private List<TestObjectDTO> lObject;
    private List<TestObjectDTO> filteredObject;


    public ListAdapter(Context context, int textViewResourceId,
                       List<TestObjectDTO> objects) {
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

        TestObjectDTO testObjectDTO = filteredObject.get(position);

        holder.id.setText(String.valueOf(testObjectDTO.getId()));

        holder.nome.setText(testObjectDTO.getName());

        holder.data.setText(testObjectDTO.getDate());

        return row;

    }

    public List<TestObjectDTO> getFilteredObject() {
        return filteredObject;
    }

    public void setFilteredObject(List<TestObjectDTO> filteredObject) {
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

    public List<TestObjectDTO> getlObjects() {
        return filteredObject;
    }

    public void setlObjects(List<TestObjectDTO> lNoticias) {
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
