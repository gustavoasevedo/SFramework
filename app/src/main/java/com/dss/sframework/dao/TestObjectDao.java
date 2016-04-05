package com.dss.sframework.dao;

import android.content.Context;

import com.dss.sframework.exceptions.InvalidTypeException;
import com.dss.sframework.model.TestObject;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Created by digipronto on 05/04/16.
 */


public class TestObjectDao extends BaseTable {

    private static TestObjectDao instance;

    public TestObjectDao(Context context) {
        super(context, TestObject.class);
    }

    public static TestObjectDao getInstance(Context context) {
        if (instance == null) {
            synchronized (TestObjectDao.class) {
                if (instance == null) {
                    instance = new TestObjectDao(context);
                }
            }
        }
        return instance;
    }


    public ArrayList<TestObject> selectList(){
        ArrayList<Object> objectList = new ArrayList<>();
        ArrayList<TestObject> lista = new ArrayList<>();

        try {
            objectList = selectList(TestObject.class);

            lista = new ArrayList<TestObject>();

            for(Object object : objectList){
                TestObject testObject = (TestObject) object;
                lista.add(testObject);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvalidTypeException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public TestObject selectId(int Id){

        Object object = new Object();
        TestObject testObject = new TestObject();


        ArrayList<String> fields = new ArrayList<>();
        fields.add("id");

        String[] values = {String.valueOf(Id)};

        try {

            object = selectWhere(TestObject.class,fields,values);

            testObject = (TestObject) object;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvalidTypeException e) {
            e.printStackTrace();
        }

        return testObject;

    }



}
