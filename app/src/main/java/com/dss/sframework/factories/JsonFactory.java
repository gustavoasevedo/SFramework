package com.dss.sframework.factories;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by gustavo.vieira on 07/05/2015.
 */
public abstract class JsonFactory {

    /**
     * Receive a generic object and return a json String
     *
     * @param object
     * @return
     * @throws JSONException
     */
    public static JSONObject getJsonObject(Object object) throws JSONException{

        Field[] objVar = object.getClass().getDeclaredFields();
        JSONObject j = new JSONObject();

        ArrayList<Object> array = new ArrayList<>();

        for(int i = 0; i < objVar.length; i++){
            objVar[i].setAccessible(true);
            Object o = new Object();
            try {
                o = objVar[i].get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            array.add(o);
        }

        for(int i = objVar.length - 1; i >= 0; i--){

            //Verify if variable is numeric
            if (objVar[i].getType() == Integer.class || objVar[i].getType() == Long.class || objVar[i].getType() == Double.class || objVar[i].getType() == int.class ){

                j.put(objVar[i].getName(), Integer.valueOf(array.get(i).toString()));

                //Verify if variable is text
            }else if (objVar[i].getType() == String.class || objVar[i].getType() == char.class) {

                j.put(objVar[i].getName(), array.get(i).toString());

                //Verify if variable is boolean
            }else if(objVar[i].getType() == Boolean.class ){

                j.put(objVar[i].getName(), Boolean.valueOf(array.get(i).toString()));
            }

        }

        return j;
    }


}
