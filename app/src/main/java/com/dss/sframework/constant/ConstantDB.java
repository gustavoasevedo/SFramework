package com.dss.sframework.constant;

/**
 * Created by gustavo.vieira on 11/05/2015.
 */
public abstract class ConstantDB {

    private static final String dbName = "testeDB"; // Database Name
    private static final int version = 1; // Database Version

    public static int getVersion() {
        return version;
    }

    public static String getDbName() {
        return dbName;
    }
}
