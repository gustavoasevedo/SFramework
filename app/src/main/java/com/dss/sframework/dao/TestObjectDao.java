package com.dss.sframework.dao;

import android.content.Context;

import com.dss.sdatabase.dao.BaseTable;
import com.dss.sdatabase.exceptions.InvalidTypeException;
import com.dss.sframework.model.dto.TestObjectDTO;
import com.dss.sframework.tools.constant.ConstantDB;
import com.dss.sframework.model.entity.TestObject;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Created by digipronto on 05/04/16.
 */


public class TestObjectDao extends BaseTable {

    private static TestObjectDao instance;

    public TestObjectDao(Context context) {
        super(context, TestObject.class, ConstantDB.dbName,ConstantDB.version);
        createTable();
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

    public void insertObject(TestObjectDTO testObject){

        ArrayList<Object> objectArrayList = new ArrayList<>();

        objectArrayList.add(testObject);

        insert(objectArrayList);
    }

    public void insertListObject(ArrayList<TestObjectDTO> testObjectList){

        ArrayList<Object> objectArrayList = new ArrayList<>();

        for(Object object: testObjectList){
            objectArrayList.add(object);
        }

        insert(objectArrayList);
    }


    public TestObjectDTO selectId(int Id){

        TestObjectDTO testObject = new TestObjectDTO();
        ArrayList<String> fields = new ArrayList<>();
        fields.add("id");
        String[] values = {String.valueOf(Id)};

        testObject = selectWhereObject(fields,values);

        return testObject;

    }


    public ArrayList<TestObjectDTO> selectList(){
        ArrayList<Object> objectList = new ArrayList<>();
        ArrayList<TestObjectDTO> lista = new ArrayList<>();

        objectList = selectNoWhere();

        lista = mountObjectList(objectList);

        return lista;
    }


    public ArrayList<TestObjectDTO> selectListbyName(String nome){

        ArrayList<Object> objectList = new ArrayList<>();
        ArrayList<TestObjectDTO> lista = new ArrayList<>();

        ArrayList<String> fields = new ArrayList<>();
        fields.add("name");
        String[] values = {nome};

        objectList = selectWhereArray(fields,values);

        lista = mountObjectList(objectList);

        return lista;
    }


    public TestObjectDTO selectWhereObject(ArrayList<String> fields,String[] values){

        TestObjectDTO testObject = new TestObjectDTO();

        try {

            testObject = (TestObjectDTO) selectWhere(TestObject.class,fields,values);

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

    public ArrayList<Object> selectWhereArray(ArrayList<String> fields,String[] values){

        ArrayList<Object> objectList = new ArrayList<>();

        try {

            objectList = selectListWhere(TestObject.class,fields,values);

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

        return objectList;
    }

    public ArrayList<Object> selectNoWhere(){

        ArrayList<Object> objectList = new ArrayList<>();

        try {

            objectList = selectList(TestObject.class);

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

        return objectList;
    }


    public ArrayList<TestObjectDTO> mountObjectList(ArrayList<Object> objectList){

        ArrayList<TestObjectDTO> lista = new ArrayList<>();

        for(Object object : objectList){
            TestObjectDTO testObject = new TestObjectDTO((TestObject) object);
            lista.add(testObject);
        }

        return lista;
    }

}
