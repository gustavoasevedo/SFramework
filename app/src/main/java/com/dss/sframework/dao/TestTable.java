package com.dss.sframework.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dss.sframework.annotations.BaseDBFlag;
import com.dss.sframework.annotations.BaseDBName;
import com.dss.sframework.annotations.BaseDBPrimaryKey;
import com.dss.sframework.annotations.BaseDBType;
import com.dss.sframework.exceptions.InvalidTypeException;
import com.dss.sframework.model.BDCreate;
import com.dss.sframework.model.TestObject;
import com.dss.sframework.constant.ConstantDB;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavo.vieira on 04/05/2015.
 */
public class TestTable {

    public BaseDB baseDB;
    private SQLiteDatabase db = null;
    private Context context;

    private final String table = "teste";
    private ArrayList<String> fields;
    private ArrayList<String> types;
    private String pk;


    public TestTable(Context context){
        super();
        baseDB = new BaseDB(context, ConstantDB.getDbName(), ConstantDB.getVersion());
        this.context = context;
        Field[] f = TestObject.class.getDeclaredFields();

        fields = new ArrayList<>();
        types = new ArrayList<>();

        for(int i = 0; i < f.length; i++){
            f[i].setAccessible(true);
            if (f[i].isAnnotationPresent(BaseDBFlag.class)){
                fields.add(f[i].getAnnotation(BaseDBName.class).value());
                types.add(f[i].getAnnotation(BaseDBType.class).value());
            }
            if(f[i].isAnnotationPresent(BaseDBPrimaryKey.class)){
                pk = f[i].getAnnotation(BaseDBName.class).value();
            }
        }

    }


    public void create(){
        ArrayList<BDCreate> bdCreates = new ArrayList<>();
        BDCreate bdCreate;

        for(int i = 0; i < fields.size(); i++) {
            bdCreate = new BDCreate();
            bdCreate.setFieldName(fields.get(i));
            bdCreate.setFieldType(types.get(i));

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


    public void insert(List<TestObject> testObject){

        openCoonection();
        Object insertObject;
        for(TestObject object : testObject) {
            insertObject = object;
            try {
                baseDB.insert(table, insertObject);
            } catch (InvalidTypeException invalidTypeException) {
                invalidTypeException.printStackTrace();
            }
        }
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
                testObject.setId(c.getInt(c.getColumnIndex("id")));
                testObject.setName(c.getString((c.getColumnIndex("name"))));
                testObject.setDate(c.getString((c.getColumnIndex("date"))));

                Listtest.add(testObject);

            }
        } finally {
            c.close();
        }
        return Listtest;

    }

    public TestObject selectWhere(int id){
        TestObject testObject = new TestObject();
        ArrayList<String> field = new ArrayList<>();
        field.add("id");

        String[] values = {String.valueOf(id)};

        Cursor c = baseDB.getWhere(table, fields, field, values);

        try {
            if (c.moveToNext()) {
                testObject.setId(c.getInt(c.getColumnIndex("id")));
                testObject.setName(c.getString((c.getColumnIndex("name"))));
                testObject.setDate(c.getString((c.getColumnIndex("date"))));
            }
        } finally {
            c.close();
        }
        return testObject;
    }

    public ArrayList<TestObject> selectListWhere(String name){

        ArrayList<TestObject> lTest = new ArrayList<>();
        TestObject testObject;
        ArrayList<String> field = new ArrayList<>();

        try {
            field.add(TestObject.class.getField("id").getName());
        }catch (NoSuchFieldException e){

        }

        String[] values = {name};

        openCoonection();
        Cursor c = baseDB.getWhere(table, fields, field, values);

        try {
            while (c.moveToNext()) {
                testObject = new TestObject();
                testObject.setId(c.getInt(c.getColumnIndex("id")));
                testObject.setName(c.getString((c.getColumnIndex("name"))));
                testObject.setDate(c.getString((c.getColumnIndex("date"))));


                lTest.add(testObject);
            }
        } finally {
            c.close();
        }
        return lTest;
    }


}
