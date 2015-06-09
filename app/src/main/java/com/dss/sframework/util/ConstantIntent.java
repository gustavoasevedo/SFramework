package com.dss.sframework.util;

/**
 * Created by gustavo.vieira on 28/05/2015.
 */
public abstract class ConstantIntent {

    private static final String INTENTPATH = "com.dss.sframework";


    private static final String MAIN = getINTENTPATH() + ".MainActivity";

    private static final String DEMOFRAGMENT = getINTENTPATH() + ".DemoFragment";

    public static String getINTENTPATH() {
        return INTENTPATH;
    }

    public static String getMAIN() {
        return MAIN;
    }

    public static String getDEMOFRAGMENT() {
        return DEMOFRAGMENT;
    }
}
