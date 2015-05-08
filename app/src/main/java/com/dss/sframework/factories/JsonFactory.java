package com.dss.sframework.factories;

import android.content.Context;

import com.dss.sframework.objects.TestObject;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * Created by gustavo.vieira on 07/05/2015.
 */
public class JsonFactory {

    public String getJsonTestObject(TestObject testObject,  Context context) throws JSONException{

        JSONObject j = new JSONObject();
        j.put("id", testObject.getId());
        j.put("name", testObject.getName());
        j.put("data", testObject.getDate());

        String resp = j.toString();

        return resp;
    }
}
