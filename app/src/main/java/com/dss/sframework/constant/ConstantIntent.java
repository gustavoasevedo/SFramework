package com.dss.sframework.constant;

/**
 * Created by gustavo.vieira on 28/05/2015.
 */
public abstract class ConstantIntent {

    private static final String INTENTPATH = "com.dss.sframework";

    private static final String ACTIVITYPATH = getINTENTPATH() + ".activity";

    private static final String FRAGMENTPATH = getINTENTPATH() + ".fragment";

    private static final String MAIN = getACTIVITYPATH() + ".MainActivity";

    private static final String DEMOFRAGMENT = getFRAGMENTPATH() + ".DemoFragment";

    private static final String LISTFRAGMENT = getFRAGMENTPATH() + ".ListFragment";

    public static String getINTENTPATH() {
        return INTENTPATH;
    }

    public static String getMAIN() {
        return MAIN;
    }

    public static String getDEMOFRAGMENT() {
        return DEMOFRAGMENT;
    }

    public static String getACTIVITYPATH() {
        return ACTIVITYPATH;
    }

    public static String getFRAGMENTPATH() {
        return FRAGMENTPATH;
    }

    public static String getLISTFRAGMENT() {
        return LISTFRAGMENT;
    }
}
