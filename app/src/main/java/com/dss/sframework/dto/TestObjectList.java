package com.dss.sframework.dto;

import com.dss.sframework.model.TestObject;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TestObjectList {

    public static String setHeaderJson(String header, String json){
        return "{" + '"' + header + '"' + ":" + json + "}";
    }

    @SerializedName("TestObjectList")
    public ArrayList<TestObject> list = new ArrayList<>();

}
