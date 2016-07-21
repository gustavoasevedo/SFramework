package com.dss.sframework.model.entity;
import com.dss.sdatabase.annotations.BaseDBFieldName;
import com.dss.sdatabase.annotations.BaseDBMethodGetName;
import com.dss.sdatabase.annotations.BaseDBMethodSetName;
import com.dss.sdatabase.annotations.BaseDBPrimaryKey;
import com.dss.sdatabase.annotations.BaseDBType;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gustavo.vieira on 04/05/2015.
 */

public class TestObject{

    @SerializedName("id")
    @BaseDBFieldName("id")
    @BaseDBType("INTEGER")
    @BaseDBPrimaryKey
    protected int id;

    @SerializedName("name")
    @BaseDBFieldName("name")
    @BaseDBType("TEXT")
    protected String name;

    @SerializedName("date")
    @BaseDBFieldName("date")
    @BaseDBType("TEXT")
    protected String date;

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

}
