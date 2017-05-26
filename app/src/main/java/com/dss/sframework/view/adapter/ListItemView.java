package com.dss.sframework.view.adapter;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dss.sframework.R;
import com.dss.sframework.model.dto.TestObjectDTO;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by digipronto on 26/07/16.
 */

@EViewGroup(R.layout.item_list)
public class ListItemView extends RelativeLayout {

    @ViewById
    TextView txtListId;

    @ViewById
    TextView txtListNome;

    public ListItemView(Context context) {
        super(context);
    }

    public void bind(TestObjectDTO testObject) {
        txtListId.setText(String.valueOf(testObject.getId()));
        txtListNome.setText(testObject.getName());
    }
}
