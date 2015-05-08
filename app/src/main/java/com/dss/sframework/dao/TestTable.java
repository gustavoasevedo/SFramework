package com.dss.sframework.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dss.sframework.objects.BDCreate;
import com.dss.sframework.objects.TestObject;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by gustavo.vieira on 04/05/2015.
 */
public class TestTable {

    public BaseDB baseDB;
    private SQLiteDatabase db = null;
    private Context context;

    private final String table = "teste";
    private final String[] fields = {"id","name","date"};
    private final String[] types = {"INTEGER","TEXT","TEXT"};
    private final String pk = "id";


    public TestTable(Context context){
        super();
        baseDB = new BaseDB(context);
        this.context = context;
    }


    public void create(){
        ArrayList<BDCreate> bdCreates = new ArrayList<>();
        BDCreate bdCreate;

        for(int i = 0; i < fields.length; i++) {
            bdCreate = new BDCreate();
            bdCreate.setFieldName(fields[i]);
            bdCreate.setFieldType(types[i]);

            bdCreates.add(bdCreate);

        }

        openCoonection();
        baseDB.createTable(db, table, bdCreates, pk);
        closeConnection();
    }

    public void openCoonection(){
        this.baseDB = new BaseDB(context);
        baseDB.setTable(table);
        db = baseDB.getWritableDatabase();
    }

    private void closeConnection() {
        try {
            if (null != db) {
                db.close();
                db = null;
                baseDB.close();
            }

        } catch (Exception e) {
            Log.e("Erro:", "Erro ao fechar conexoes");
        }
    }


    public void insert(TestObject testObject){

//        ContentValues values = new ContentValues();

        Object clazz = testObject;
        Field[] objVar = clazz.getClass().getDeclaredFields();
        String[] supportArray = fields.clone();
        ArrayList<Object> obj = new ArrayList<>();

        for(int i = 0; i < objVar.length; i++){
            Object o = new Object();
            try {
                 o = objVar[i].get(clazz);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            obj.add(o);
        }

        for(int i = 0; i < supportArray.length / 2; i++)
        {
            String temp = supportArray[i];
            supportArray[i] = supportArray[supportArray.length - i - 1];
            supportArray[supportArray.length - i - 1] = temp;
        }


        openCoonection();
        baseDB.insert(table, supportArray, objVar,obj);
        closeConnection();

    }

    public void update(){
        openCoonection();
        baseDB.newUpdate();
        closeConnection();
    }

    public ArrayList<TestObject> selectList(){
        ArrayList<TestObject> Listtest = new ArrayList<>();
        TestObject testObject;

        openCoonection();

        Cursor c = baseDB.get(table,fields);

        try {
            while (c.moveToNext()) {
                testObject = new TestObject();
                testObject.setId(c.getInt(0));
                testObject.setName(c.getString(1));
                testObject.setDate(c.getString(2));

                Listtest.add(testObject);

            }
        } finally {
            c.close();
        }
        return Listtest;

    }

    public TestObject selectWhere(int id){
        TestObject testObject = new TestObject();
        String[] field = {fields[0]};
        String[] values = {String.valueOf(id)};

        Cursor c = baseDB.getWhere(table, fields, field, values);

        try {
            if (c.moveToNext()) {
                testObject.setId(c.getInt(0));
                testObject.setName(c.getString(1));
                testObject.setDate(c.getString(2));
            }
        } finally {
            c.close();
        }
        return testObject;
    }

    public ArrayList<TestObject> selectListWhere(String name){

        ArrayList<TestObject> lTest = new ArrayList<>();
        TestObject testObject;
        String[] field = {fields[1]};
        String[] values = {name};

        openCoonection();
        Cursor c = baseDB.getWhere(table, fields, field, values);

        try {
            while (c.moveToNext()) {
                testObject = new TestObject();
                testObject.setId(c.getInt(0));
                testObject.setName(c.getString(1));
                testObject.setDate(c.getString(2));


                lTest.add(testObject);
            }
        } finally {
            c.close();
        }
        return lTest;
    }


}
