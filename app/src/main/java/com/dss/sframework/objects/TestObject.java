package com.dss.sframework.objects;
import com.dss.sframework.annotations.BaseDBFlag;
import com.dss.sframework.annotations.BaseDBName;
import com.dss.sframework.annotations.BaseDBPrimaryKey;
import com.dss.sframework.annotations.BaseDBType;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by gustavo.vieira on 04/05/2015.
 */

public class TestObject implements Serializable {

    @SerializedName("id")
    @BaseDBName("id")
    @BaseDBType("INTEGER")
    @BaseDBPrimaryKey
    @BaseDBFlag
    private int id;

    @SerializedName("name")
    @BaseDBName("name")
    @BaseDBType("TEXT")
    @BaseDBFlag
    private String name;

    @SerializedName("date")
    @BaseDBName("date")
    @BaseDBType("TEXT")
    @BaseDBFlag
    private String date;

    private boolean test;

    public TestObject(int id, String name, String date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public TestObject(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TestObject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

}
