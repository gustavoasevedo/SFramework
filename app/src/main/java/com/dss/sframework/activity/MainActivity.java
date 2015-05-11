package com.dss.sframework.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dss.sframework.dao.TestTable;
import com.dss.sframework.factories.JsonFactory;
import com.dss.sframework.helper.MainHelper;
import com.dss.sframework.objects.TestObject;
import com.dss.sframework.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TestTable testTable = new TestTable(MainActivity.this);
        testTable.create();


        initLayout();
    }

    public void initLayout(){
        MainHelper helper = new MainHelper();
        helper.MainActivity(this);
        helper.setClickListener(insertClick, selectClick,jsonClick,jsonListClick);
    }

    public View.OnClickListener selectClick = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            TestTable testTable = new TestTable(MainActivity.this);
            TestObject testObject = testTable.selectWhere(4);
            Toast.makeText(MainActivity.this,testObject.toString(),Toast.LENGTH_LONG).show();
        }
    };


    public View.OnClickListener insertClick = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            TestTable testTable = new TestTable(MainActivity.this);
            TestObject testObject = new TestObject(4,"nome","04-05-2015");
            testTable.insert(testObject);
            Toast.makeText(MainActivity.this,"Inserido com sucesso",Toast.LENGTH_LONG).show();
        }
    };

    public View.OnClickListener jsonListClick = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            TestObject testObject = new TestObject(4,"nome","04-05-2015");
            TestObject testObject2 = new TestObject(4,"nome do zé","05-05-2015");
            TestObject testObject3 = new TestObject(4,"nome do zé antonho","06-05-2015");

            ArrayList<Object> arrayList = new ArrayList<>();
            arrayList.add(testObject2);
            arrayList.add(testObject3);


            JsonFactory jsonFactory = new JsonFactory();
            Object send = testObject;
            JSONObject json= null;

            try {
                json = jsonFactory.getJsonObject(send);
                JSONArray jsonArray = new JSONArray();
                for(Object i:arrayList){
                    jsonArray.put(jsonFactory.getJsonObject(i));
                }

                json.put("Lista",jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            String result = json.toString();

            Toast.makeText(MainActivity.this,result,Toast.LENGTH_LONG).show();
        }
    };

    public View.OnClickListener jsonClick = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            TestObject testObject = new TestObject(4,"nome","04-05-2015");


            JsonFactory jsonFactory = new JsonFactory();
            Object send = testObject;
            JSONObject jsonObject = null;
            try {

                jsonObject = jsonFactory.getJsonObject(send);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            String json = jsonObject.toString();

            Toast.makeText(MainActivity.this,json,Toast.LENGTH_LONG).show();
        }
    };


}
