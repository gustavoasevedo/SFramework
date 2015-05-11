package com.dss.sframework.factories;

import android.content.Context;

import com.dss.sframework.objects.TestObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by gustavo.vieira on 07/05/2015.
 */
public class JsonFactory {

    /**
     * Receive a generic object and return a json String
     *
     * @param object
     * @return
     * @throws JSONException
     */
    public JSONObject getJsonObject(Object object) throws JSONException{

        Field[] objVar = object.getClass().getDeclaredFields();
        JSONObject j = new JSONObject();

        ArrayList<Object> array = new ArrayList<>();

        for(int i = 0; i < objVar.length; i++){
            Object o = new Object();
            try {
                o = objVar[i].get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            array.add(o);
        }

        for(int i = objVar.length - 1; i >= 0; i--){
            j.put(objVar[i].getName(), String.valueOf(array.get(i).toString()));
        }

        return j;
    }
}
