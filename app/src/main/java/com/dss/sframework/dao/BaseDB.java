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

    private  String database ; // Database Name
    private int version; // Database Version
    private String table;
    Context context;


    public BaseDB(Context context,String dbName, int dbVersion) {
        super(context, dbName, null, dbVersion);
        this.context = context;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE IF EXISTS ");
        sb.append(getTable());
        db.execSQL(sb.toString());
        onCreate(db);
    }

    /**
     * Create a table based on received parameters
     *
     * @param db Sqlite Database
     * @param table Name of the table
     * @param coluns Colluns of the table
     * @param pk Table Primary Key
     */
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

    /**
     * Receive values from another class and insert on a table.
     *
     * @param table Name of the table
     * @param fields Colluns of the table
     * @param insertObject Generic Object with the values will be put in the table
     * @return
     */
    public Long insert(String table, String[] fields, Object insertObject) {

        Field[] objVar = insertObject.getClass().getDeclaredFields();
        ArrayList<Object> array = new ArrayList<>();

        for(int i = 0; i < objVar.length; i++){
            Object o = new Object();
            try {
                o = objVar[i].get(insertObject);
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


    /**
     * Receive table and coluns to return a generic select on the table
     *
     * @param table Name of the table
     * @param colluns Colluns of the table
     * @return
     */
    public Cursor get(String table, String[] colluns) {

        Cursor c = getWritableDatabase().query(table, colluns, null, null,
                null, null, null);

        return c;
    }


    /**
     * Receive table, coluns, and one or more arguments and run a filtered select on table.
     *
     * @param table Name of the table
     * @param coluns Colluns of the table
     * @param fields Name of the fields who will be the filter
     * @param args Values of the filter
     * @return
     */
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

    public void setDatabase(String database) {
        this.database = database;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }


}
