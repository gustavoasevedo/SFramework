package com.dss.sframework.model.TestObject;
import com.dss.sdatabase.annotations.BaseDBFieldName;
import com.dss.sdatabase.annotations.BaseDBMethodGetName;
import com.dss.sdatabase.annotations.BaseDBMethodSetName;
import com.dss.sdatabase.annotations.BaseDBPrimaryKey;
import com.dss.sdatabase.annotations.BaseDBType;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by gustavo.vieira on 04/05/2015.
 */

public class TestObject implements Serializable {

    @SerializedName("id")
    @BaseDBFieldName("id")
    @BaseDBType("INTEGER")
    @BaseDBPrimaryKey
    private int id;

    @SerializedName("name")
    @BaseDBFieldName("name")
    @BaseDBType("TEXT")
    private String name;

    @SerializedName("date")
    @BaseDBFieldName("date")
    @BaseDBType("TEXT")
    private String date;

    public TestObject(int id, String name, String date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public TestObject(){

    }

    @BaseDBMethodGetName("id")
    public int getId() {
        return id;
    }

    @BaseDBMethodSetName("id")
    public void setId(int id) {
        this.id = id;
    }



    @BaseDBMethodGetName("name")
    public String getName() {
        return name;
    }

    @BaseDBMethodSetName("name")
    public void setName(String name) {
        this.name = name;
    }



    @BaseDBMethodGetName("date")
    public String getDate() {
        return date;
    }

    @BaseDBMethodSetName("date")
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
