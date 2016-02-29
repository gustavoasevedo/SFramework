package com.dss.sframework.constant;

import android.content.Context;

import com.dss.sframework.dao.TestTable;

/**
 * Created by gustavo.vieira on 11/05/2015.
 */
public abstract class ConstantDB {

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
