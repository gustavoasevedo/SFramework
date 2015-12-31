package com.dss.sframework.objects.TestObject;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gustavo.vieira on 04/05/2015.
 */
public class TestObject {

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("date")
    public String date;

    public TestObject(int id, String name, String date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public TestObject(){

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TestObject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
