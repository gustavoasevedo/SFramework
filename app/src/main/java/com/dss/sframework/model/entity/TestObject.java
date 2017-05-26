package com.dss.sframework.model.entity;
import com.dss.sdatabase.annotations.BaseDBFieldName;
import com.dss.sdatabase.annotations.BaseDBPrimaryKey;
import com.dss.sdatabase.annotations.BaseDBType;
import com.dss.sframework.model.dto.TestObjectDTO;
import com.google.gson.annotations.SerializedName;

public class TestObject{

    @SerializedName("id_Format")
    @BaseDBFieldName("id_Format")
    @BaseDBType("INTEGER")
    @BaseDBPrimaryKey
    protected int id;

    @SerializedName("format_Name")
    @BaseDBFieldName("format_Name")
    @BaseDBType("TEXT")
    protected String name;


    public TestObject(){

    }

    public TestObject(TestObject testObject){
        this.id = testObject.getId();
        this.name = testObject.getName();
    }

    public TestObject(TestObjectDTO testObjectDTO){
        this.id = testObjectDTO.getId();
        this.name = testObjectDTO.getName();
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



}
