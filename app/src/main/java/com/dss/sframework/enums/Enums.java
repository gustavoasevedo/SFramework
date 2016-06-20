package com.dss.sframework.enums;

/**
 * Created by rodrigo on 06/08/2014.
 */
public class Enums {

    public enum MEDIA_TYPE {
        CAMERA(0), VIDEO(1);

        private final int value;

        MEDIA_TYPE(final int newValue) {
            value = newValue;
        }

        public int getValue() {
            return value;
        }
    }
}