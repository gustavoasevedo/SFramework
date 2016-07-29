package com.dss.sframework.model.interfaces;

import com.dss.sframework.model.dto.TestObjectDTO;

import java.util.List;

/**
 * Created by digipronto on 26/07/16.
 */
public interface TestObjectFinder {

    List<TestObjectDTO> findAll();

}
