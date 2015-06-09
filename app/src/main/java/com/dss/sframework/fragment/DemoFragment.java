package com.dss.sframework.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dss.sframework.R;
import com.dss.sframework.dao.TestTable;
import com.dss.sframework.factories.ImageFactory;
import com.dss.sframework.factories.JsonFactory;
import com.dss.sframework.helper.DemoFragmentHelper;
import com.dss.sframework.objects.TestObject;
import com.dss.sframework.util.ConstantIntent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by gustavo.vieira on 22/05/2015.
 */
public class DemoFragment extends Fragment {

    Context context;
    Activity activity;
    String image;
    DemoFragmentHelper helper;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.demofragment, container, false);

        context = getActivity();
        activity = ((Activity)context);

        helper = new DemoFragmentHelper();

        TestTable testTable = new TestTable(context);
        testTable.create();

        initLayout();

        return view;
    }

    public void initLayout(){
        helper.DemoFragment(view);
        helper.setClickListener(insertClick, selectClick, jsonClick, jsonListClick, toBase64Click, fromBase64Click,intentClick);
    }

    public View.OnClickListener selectClick = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            TestTable testTable = new TestTable(context);
            TestObject testObject = testTable.selectWhere(4);
            Toast.makeText(context, testObject.toString(), Toast.LENGTH_LONG).show();
        }
    };


    public View.OnClickListener insertClick = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            TestTable testTable = new TestTable(context);
            TestObject testObject = new TestObject(4,"nome","04-05-2015");
            testTable.insert(testObject);
            Toast.makeText(context,"Inserido com sucesso",Toast.LENGTH_LONG).show();
        }
    };

    public View.OnClickListener jsonListClick = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            TestObject testObject = new TestObject(4,"nome","04-05-2015");
            TestObject testObject2 = new TestObject(5,"nome do z�","05-05-2015");
            TestObject testObject3 = new TestObject(6,"nome do z� antonho","06-05-2015");

            ArrayList<Object> arrayList = new ArrayList<>();
            arrayList.add(testObject2);
            arrayList.add(testObject3);


            Object send = testObject;
            JSONObject json= null;

            try {
                json = JsonFactory.getJsonObject(send);
                JSONArray jsonArray = new JSONArray();
                for(Object i:arrayList){
                    jsonArray.put(JsonFactory.getJsonObject(i));
                }

                json.put("Lista",jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            String result = json.toString();

            Toast.makeText(context,result,Toast.LENGTH_LONG).show();
        }
    };

    public View.OnClickListener jsonClick = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            TestObject testObject = new TestObject(4,"nome","04-05-2015");


            Object send = testObject;
            JSONObject jsonObject = null;
            try {

                jsonObject = JsonFactory.getJsonObject(send);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            String json = jsonObject.toString();

            Toast.makeText(context,json,Toast.LENGTH_LONG).show();
        }
    };

    public View.OnClickListener toBase64Click = new View.OnClickListener(){
        @Override
        public void onClick(View v){

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

            String base64 = ImageFactory.convertPhotoToBase64(bitmap);

            image = base64;

            Toast.makeText(context,base64,Toast.LENGTH_LONG).show();

            helper.btnfromBase64.setVisibility(View.VISIBLE);

        }
    };

    public View.OnClickListener fromBase64Click = new View.OnClickListener(){
        @Override
        public void onClick(View v){


            Bitmap bitmap = ImageFactory.convertPhotoFromBase64(image, context);

            Drawable thumb = new BitmapDrawable(getResources(), bitmap);

            helper.imgBase64.setImageDrawable(thumb);
        }
    };

    public View.OnClickListener intentClick = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Toast.makeText(context, ConstantIntent.getMAIN(),Toast.LENGTH_LONG).show();
        }
    };


}
