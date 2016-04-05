package com.dss.sframework.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dss.sframework.annotations.BaseDBFieldName;
import com.dss.sframework.annotations.BaseDBMethodSetName;
import com.dss.sframework.annotations.BaseDBPrimaryKey;
import com.dss.sframework.annotations.BaseDBType;
import com.dss.sframework.constant.ConstantDB;
import com.dss.sframework.constant.ConstantException;
import com.dss.sframework.exceptions.InvalidTypeException;
import com.dss.sframework.model.BDCreate;
import com.dss.sframework.model.TestObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavo.vieira on 04/05/2015.
 */
public class BaseTable {

    public BaseDB baseDB;
    private SQLiteDatabase db = null;
    private Context context;

    private String tableName;
    private ArrayList<String> fields;
    private ArrayList<String> types;
    private String pk;


    public BaseTable(Context context, Class modelClass){
        super();
        baseDB = new BaseDB(context, ConstantDB.getDbName(), ConstantDB.getVersion());
        this.context = context;
        Field[] f = modelClass.getDeclaredFields();

        tableName = modelClass.getSimpleName();

        fields = new ArrayList<>();
        types = new ArrayList<>();

        for(int i = 0; i < f.length; i++){
            f[i].setAccessible(true);
            if (f[i].isAnnotationPresent(BaseDBFieldName.class)){
                fields.add(f[i].getAnnotation(BaseDBFieldName.class).value());
                types.add(f[i].getAnnotation(BaseDBType.class).value());
            }
            if(f[i].isAnnotationPresent(BaseDBPrimaryKey.class)){
                pk = f[i].getAnnotation(BaseDBFieldName.class).value();
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
        baseDB.createTable(db, tableName, bdCreates, pk);
        closeConnection();
    }

    private void openCoonection(){
        this.baseDB = new BaseDB(context, ConstantDB.getDbName(), ConstantDB.getVersion());
        baseDB.setTable(tableName);
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
                baseDB.insert(tableName, insertObject);
            } catch (InvalidTypeException invalidTypeException) {
                invalidTypeException.printStackTrace();
            }
        }
        closeConnection();

    }

    protected ArrayList<Object> selectList(Class aClass)
            throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException,
            InstantiationException, InvalidTypeException {

        Class<?> clazz = Class.forName(aClass.getName());
        Constructor<?> ctor = clazz.getConstructor();
        ctor.setAccessible(true);

        ArrayList<Object> list = new ArrayList<>();
        Object object;


        openCoonection();

        Cursor c = baseDB.get(tableName,fields);

        try {
            while (c.moveToNext()) {
                object = ctor.newInstance();

                Method[] methods = aClass.getMethods();

                for(int i = 0;i < methods.length;i++) {
                    methods[i].setAccessible(true);


                    if (methods[i].isAnnotationPresent(BaseDBMethodSetName.class)) {

                            Type parameterizedType = (Type) methods[i].getGenericParameterTypes()[0];

                        if (parameterizedType == Integer.class||
                                parameterizedType == int.class) {

                            methods[i].invoke(object,c.getInt(c.getColumnIndex(methods[i].getAnnotation(BaseDBMethodSetName.class).value())));


                        } else if (parameterizedType == Long.class) {

                            methods[i].invoke(object,c.getLong(c.getColumnIndex(methods[i].getAnnotation(BaseDBMethodSetName.class).value())));

                        }else if (parameterizedType == Double.class) {

                            methods[i].invoke(object,c.getDouble(c.getColumnIndex(methods[i].getAnnotation(BaseDBMethodSetName.class).value())));

                            //Verify if variable is text
                        }else if (parameterizedType == String.class ||
                                parameterizedType == char.class) {

                            methods[i].invoke(object,c.getString(c.getColumnIndex(methods[i].getAnnotation(BaseDBMethodSetName.class).value())));

                        }else{
                            throw new InvalidTypeException(ConstantException.getINVALIDTYPEEXCEPTION());
                        }
                    }
                }

                list.add(object);

            }
        } finally {
            c.close();
        }
        return list;

    }

    protected Object selectWhere(Class aClass,ArrayList<String> field,String[] values)
            throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException,
            InstantiationException, InvalidTypeException{


        Class<?> clazz = Class.forName(aClass.getName());
        Constructor<?> ctor = clazz.getConstructor();
        ctor.setAccessible(true);

        Object object = ctor.newInstance();

        Cursor c = baseDB.getWhere(tableName, fields, field, values);

        try {
            if (c.moveToNext()) {
                Method[] methods = aClass.getMethods();

                for(int i = 0;i < methods.length;i++) {
                    methods[i].setAccessible(true);

                    if (methods[i].isAnnotationPresent(BaseDBMethodSetName.class)) {

                        Type parameterizedType = (Type) methods[i].getGenericParameterTypes()[0];

                        if (parameterizedType == Integer.class||
                                parameterizedType == int.class) {

                            methods[i].invoke(object,c.getInt(c.getColumnIndex(methods[i].getAnnotation(BaseDBMethodSetName.class).value())));


                        } else if (parameterizedType == Long.class) {

                            methods[i].invoke(object,c.getLong(c.getColumnIndex(methods[i].getAnnotation(BaseDBMethodSetName.class).value())));

                        }else if (parameterizedType == Double.class) {

                            methods[i].invoke(object,c.getDouble(c.getColumnIndex(methods[i].getAnnotation(BaseDBMethodSetName.class).value())));

                            //Verify if variable is text
                        }else if (parameterizedType == String.class ||
                                parameterizedType == char.class) {

                            methods[i].invoke(object,c.getString(c.getColumnIndex(methods[i].getAnnotation(BaseDBMethodSetName.class).value())));

                        }else{
                            throw new InvalidTypeException(ConstantException.getINVALIDTYPEEXCEPTION());
                        }
                    }
                }
            }
        } finally {
            c.close();
        }

        return object;
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
        Cursor c = baseDB.getWhere(tableName, fields, field, values);

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
