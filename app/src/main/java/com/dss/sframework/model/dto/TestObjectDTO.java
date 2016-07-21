package com.dss.sframework.model.dto;

import com.dss.sframework.model.entity.TestObject;

import java.io.Serializable;

/**
 * Created by gustavo.vieira on 04/05/2015.
 */

public class TestObjectDTO extends TestObject implements Serializable {

    public TestObjectDTO(int id, String name, String date) {
        super(id,name,date);
    }

    public TestObjectDTO(TestObject testObject) {
        super(testObject.getId(),testObject.getName(),testObject.getDate());
    }

    public TestObjectDTO(){
        super();
    }

}
