package com.dss.sframework.dao;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.dss.sframework.objects.BDCreate;;

/**
 * Created by gustavo.vieira on 04/05/2015.
 */



public class BaseDB extends SQLiteOpenHelper {

    private static final String database = "testeDB"; // Database Name
    private static final int version = 1; // Database Version
    private String table;
    Context context;

    public BaseDB(Context context) {
        super(context, database, null, version);
        this.context = context;
    }

    //Create all tables from the application
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE IF EXISTS ");
        sb.append(getTable());
        db.execSQL(sb.toString());
        onCreate(db);
    }

    public void newUpdate() {


        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE IF EXISTS ");
        sb.append(getTable());
        getWritableDatabase().execSQL(sb.toString());
        onCreate(getWritableDatabase());
    }

    //Create a table based on received parameters
    public void createTable(SQLiteDatabase db,String table,List<BDCreate> coluns,String pk){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE IF NOT EXISTS ");
        sb.append(table);
        sb.append(" ");
        sb.append("(");

        for(BDCreate field : coluns) {
            sb.append(field.getFieldName());
            sb.append(" ");
            sb.append(field.getFieldType());
            sb.append(", ");

        }
        if(pk != "" || pk != null) {
            sb.append("PRIMARY KEY(");
            sb.append(pk);
            sb.append("));");
        }
        else{
            sb.append(");");
        }

        db.execSQL(sb.toString());
    }

    //Receive values from another class and insert on a table
    public Long insert(String table, String[] fields, Object clazz) {

        Field[] objVar = clazz.getClass().getDeclaredFields();
        ArrayList<Object> array = new ArrayList<>();

        for(int i = 0; i < objVar.length; i++){
            Object o = new Object();
            try {
                o = objVar[i].get(clazz);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            array.add(o);
        }

        for(int i = 0; i < fields.length / 2; i++)
        {
            String temp = fields[i];
            fields[i] = fields[fields.length - i - 1];
            fields[fields.length - i - 1] = temp;
        }

        ContentValues values = new ContentValues();

        for (int i = fields.length - 1; i >= 0; i--) {

            //Verify if variable is numeric
            if (objVar[i].getType() == Integer.class || objVar[i].getType() == Long.class || objVar[i].getType() == Double.class || objVar[i].getType() == int.class ){

                values.put(fields[i], Integer.valueOf(array.get(i).toString()));

                //Verify if variable is text
            }else if (objVar[i].getType() == String.class || objVar[i].getType() == char.class) {

                values.put(fields[i], array.get(i).toString());

                //Verify if variable is boolean
            }else if(objVar[i].getType() == Boolean.class ){

                values.put(fields[i], Boolean.valueOf(array.get(i).toString()));
            }
        }

        Long row = getWritableDatabase().replace(table, null, values);
        Log.d(table, row.toString());
        return row;
    }


    //Receive table and coluns to return a generic select on the table
    public Cursor get(String table, String[] coluns) {

        Cursor c = getWritableDatabase().query(table, coluns, null, null,
                null, null, null);

        return c;
    }


    //Receive table, coluns, and one or more arguments and run a filtered select on table.
    public Cursor getWhere(String table, String[] coluns, String[] fields,String[] args) {

        StringBuilder sb = new StringBuilder();

        for(String field: fields){
            sb.append(field);
            sb.append("= ?,");
        }
        String Sfields = sb.toString();
        String query = Sfields.substring(0, Sfields.length() - 1);

        Cursor c = getWritableDatabase().query(table, coluns, query, args,
                null, null, null);

        return c;
    }


    public String getDatabase() {
        return database;
    }

    public int getVersion() {
        return version;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
}
