package com.dss.sframework.model.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TestObjectList {

    public static String setHeaderJson(String header, String json){
        return "{" + '"' + header + '"' + ":" + json + "}";
    }

    @SerializedName("TestObjectList")
    public ArrayList<TestObjectDTO> list = new ArrayList<>();

}
