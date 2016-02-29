package com.dss.sframework.dto;

import com.dss.sframework.objects.TestObject;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TestObjectList {

    public static String setHeaderJson(String header, String json){
        return "{" + '"' + header + '"' + ":" + json + "}";
    }

    @SerializedName("TestObjectList")
    public List<TestObject> list = new ArrayList<>();

}
