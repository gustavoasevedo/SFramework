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

import org.json.JSONException;


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
        helper.setClickListener(insertClick, selectClick,jsonClick);
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
            TestObject testObject = new TestObject(4,"nome","0-05-2015");
            testTable.insert(testObject);
            Toast.makeText(MainActivity.this,"Inserido com sucesso",Toast.LENGTH_LONG).show();
        }
    };

    public View.OnClickListener jsonClick = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            TestObject testObject = new TestObject(4,"nome","0-05-2015");
            JsonFactory jsonFactory = new JsonFactory();
            Object send = testObject;
            String json= "";
            try {
                json = jsonFactory.getJsonObject(send);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Toast.makeText(MainActivity.this,json,Toast.LENGTH_LONG).show();
        }
    };


}
