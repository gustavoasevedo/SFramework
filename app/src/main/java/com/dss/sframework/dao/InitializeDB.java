package com.dss.sframework.dao;

import android.content.Context;

/**
 * Created by gustavo.vieira on 11/05/2015.
 */
public abstract class InitializeDB {

    private static final String dbName = "testeDB"; // Database Name
    private static final int version = 1; // Database Version

    public static void startDB(Context context){
        TestTable testTable = new TestTable(context);
        testTable.create();
    }

    public static int getVersion() {
        return version;
    }

    public static String getDbName() {
        return dbName;
    }
}
