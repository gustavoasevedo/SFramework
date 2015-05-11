package com.dss.sframework.helper;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import com.dss.sframework.R;

/**
 * Created by gustavo.vieira on 07/05/2015.
 */
public class MainHelper {

    public Button btInsert, btSelect,btnJson;

    public void MainActivity (Context context){
        btInsert = (Button) ((Activity)context).findViewById(R.id.btInsert);
        btSelect = (Button) ((Activity)context).findViewById(R.id.btSelect);
        btnJson = (Button) ((Activity)context).findViewById(R.id.btnJson);
    }

    public void setClickListener(View.OnClickListener insertClick,View.OnClickListener selectClick,View.OnClickListener jsonClick ){

        btInsert.setOnClickListener(insertClick);
        btSelect.setOnClickListener(selectClick);
        btnJson.setOnClickListener(jsonClick);

    }
}
