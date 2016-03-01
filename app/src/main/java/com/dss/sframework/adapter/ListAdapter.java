package com.dss.sframework.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dss.sframework.R;
import com.dss.sframework.model.TestObject;
import java.util.List;

/**
* Created by gustavo.vieira on 20/01/2015.
*/
public class ListAdapter extends ArrayAdapter<TestObject> {

    private Context context;
    private int layoutResourceId;
    private List<TestObject> lObject;


    public ListAdapter(Context context, int textViewResourceId,
                       List<TestObject> objects) {
        super(context, textViewResourceId, objects);
        this.setContext(context);
        this.setLayoutResourceId(textViewResourceId);
        this.setlObjects(objects);
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

        TestObject testObject = lObject.get(position);

        holder.id.setText(String.valueOf(testObject.getId()));

        holder.nome.setText(testObject.getName());

        holder.data.setText(testObject.getDate());

        return row;

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

    public List<TestObject> getlNoticias() {
        return lObject;
    }

    public void setlObjects(List<TestObject> lNoticias) {
        this.lObject = lNoticias;
    }
}
