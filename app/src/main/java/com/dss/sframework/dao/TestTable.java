package com.dss.sframework.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dss.sframework.objects.BDCreate;
import com.dss.sframework.objects.TestObject.TestObject;
import com.dss.sframework.constant.ConstantDB;

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

        baseDB = new BaseDB(context, ConstantDB.getDbName(), ConstantDB.getVersion());
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
        this.baseDB = new BaseDB(context, ConstantDB.getDbName(), ConstantDB.getVersion());
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

        Object insertObject = testObject;

        openCoonection();
        baseDB.insert(table, fields,insertObject);
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
