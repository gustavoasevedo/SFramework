package com.dss.sframework.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by digipronto on 29/02/16.
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface BaseDBMethodGetName {

    String value();

}
