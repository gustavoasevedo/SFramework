package com.dss.sframework.model.entity;
import com.dss.sdatabase.annotations.BaseDBFieldName;
import com.dss.sdatabase.annotations.BaseDBMethodGetName;
import com.dss.sdatabase.annotations.BaseDBMethodSetName;
import com.dss.sdatabase.annotations.BaseDBPrimaryKey;
import com.dss.sdatabase.annotations.BaseDBType;
import com.dss.sframework.model.dto.TestObjectDTO;
import com.google.gson.annotations.SerializedName;

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

    public TestObject(){

    }

    public TestObject(TestObject testObject){
        this.id = testObject.getId();
        this.name = testObject.getName();
        this.date = testObject.getDate();
    }

    public TestObject(TestObjectDTO testObjectDTO){
        this.id = testObjectDTO.getId();
        this.name = testObjectDTO.getName();
        this.date = testObjectDTO.getDate();
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
