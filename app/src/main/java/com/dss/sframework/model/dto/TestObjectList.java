package com.dss.sframework.model.dto;

import com.dss.sframework.model.interfaces.TestObjectFinder;
import com.google.gson.annotations.SerializedName;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

@EBean
public class TestObjectList implements TestObjectFinder {

    public static String setHeaderJson(String header, String json) {
        return "{" + '"' + header + '"' + ":" + json + "}";
    }

    @SerializedName("TestObjectList")
    public ArrayList<TestObjectDTO> list = new ArrayList<>();

    @Override
    public List<TestObjectDTO> findAll() {
        return list;
    }
}
