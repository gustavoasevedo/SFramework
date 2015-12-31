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
public class DemoFragmentHelper {

    public Button btInsert, btSelect,btnJson,btnJsonList,btntoBase64,btnfromBase64,btnIntent;
    public ImageView imgBase64;

    public void DemoFragment (View view){
        btInsert = (Button) view.findViewById(R.id.btInsert);
        btSelect = (Button) view.findViewById(R.id.btSelect);
        btnJson = (Button) view.findViewById(R.id.btnJson);
        btnJsonList = (Button) view.findViewById(R.id.btnJsonList);
        btntoBase64 = (Button) view.findViewById(R.id.btntoBase64);
        btnfromBase64 = (Button) view.findViewById(R.id.btnfromBase64);
        imgBase64 = (ImageView) view.findViewById(R.id.imgBase64);
        btnIntent = (Button) view.findViewById(R.id.btnIntent);
    }

    public void setClickListener(View.OnClickListener insertClick,View.OnClickListener selectClick,View.OnClickListener jsonClick,View.OnClickListener jsonListClick,
                                 View.OnClickListener toBase64Click,View.OnClickListener fromBase64Click,View.OnClickListener intentClick,View.OnClickListener imgClick){

        btInsert.setOnClickListener(insertClick);
        btSelect.setOnClickListener(selectClick);
        btnJson.setOnClickListener(jsonClick);
        btnJsonList.setOnClickListener(jsonListClick);
        btntoBase64.setOnClickListener(toBase64Click);
        btnfromBase64.setOnClickListener(fromBase64Click);
        btnIntent.setOnClickListener(intentClick);
        imgBase64.setOnClickListener(imgClick);
    }
}
