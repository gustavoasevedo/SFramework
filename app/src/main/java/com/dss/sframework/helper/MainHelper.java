package com.dss.sframework.helper;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.dss.sframework.R;

/**
 * Created by gustavo.vieira on 07/05/2015.
 */
public class MainHelper {

    public Button btInsert, btSelect,btnJson,btnJsonList,btntoBase64,btnfromBase64;
    public ImageView imgBase64;

    public void MainActivity (Context context){
        btInsert = (Button) ((Activity)context).findViewById(R.id.btInsert);
        btSelect = (Button) ((Activity)context).findViewById(R.id.btSelect);
        btnJson = (Button) ((Activity)context).findViewById(R.id.btnJson);
        btnJsonList = (Button) ((Activity)context).findViewById(R.id.btnJsonList);
        btntoBase64 = (Button) ((Activity)context).findViewById(R.id.btntoBase64);
        btnfromBase64 = (Button) ((Activity)context).findViewById(R.id.btnfromBase64);
        imgBase64 = (ImageView) ((Activity)context).findViewById(R.id.imgBase64);
    }

    public void setClickListener(View.OnClickListener insertClick,View.OnClickListener selectClick,View.OnClickListener jsonClick,View.OnClickListener jsonListClick,
                                 View.OnClickListener toBase64Click,View.OnClickListener fromBase64Click){

        btInsert.setOnClickListener(insertClick);
        btSelect.setOnClickListener(selectClick);
        btnJson.setOnClickListener(jsonClick);
        btnJsonList.setOnClickListener(jsonListClick);
        btntoBase64.setOnClickListener(toBase64Click);
        btnfromBase64.setOnClickListener(fromBase64Click);

    }
}
