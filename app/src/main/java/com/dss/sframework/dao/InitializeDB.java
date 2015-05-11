package com.dss.sframework.dao;

/**
 * Created by gustavo.vieira on 11/05/2015.
 */
public class InitializeDB {

    private final String dbName = "testeDB"; // Database Name
    private final int version = 1; // Database Version


    public int getVersion() {
        return version;
    }

    public String getDbName() {
        return dbName;
    }
}
