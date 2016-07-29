package com.dss.sframework.model.dto;

import com.dss.sframework.model.entity.TestObject;

import java.io.Serializable;

public class TestObjectDTO extends TestObject implements Serializable {

    public TestObjectDTO(TestObject testObject) {
        super(testObject);
    }

    public TestObjectDTO(){
        super();
    }

}
