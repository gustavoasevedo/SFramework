package com.dss.sframework.factories;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gustavo.vieira on 07/05/2015.
 */
public abstract class DateFactory {

    public static String getCompleteData() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
        String currentDate = sdf.format(new Date());

        return currentDate;
    }

    public static String getCompleteDataYY() {
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd hh-mm-ss");
        String currentDate = sdf.format(new Date());

        return currentDate;
    }

    public static String getyyyyMMdd(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());

        return currentDate;
    }

    public static String getddMMyyy(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String currentDate = sdf.format(new Date());

        return currentDate;
    }

    public static String getMMyyyydd(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM-yyyy-dd");
        String currentDate = sdf.format(new Date());

        return currentDate;
    }

    public static String getyyMMdd() {
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        String currentDate = sdf.format(new Date());

        return currentDate;
    }

    public static String getddMMyy() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
        String currentDate = sdf.format(new Date());

        return currentDate;
    }

    public static String MMyydd() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-yy-dd");
        String currentDate = sdf.format(new Date());

        return currentDate;
    }



    public static String gethhmmss() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh-mm-ss");
        String currentDate = sdf.format(new Date());

        return currentDate;
    }

    public static String getYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String currentDate = sdf.format(new Date());

        return currentDate;
    }

    public static String getYearYY() {
        SimpleDateFormat sdf = new SimpleDateFormat("yy");
        String currentDate = sdf.format(new Date());

        return currentDate;
    }

    public static String getMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        String currentDate = sdf.format(new Date());

        return currentDate;
    }


    public static String getDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        String currentDate = sdf.format(new Date());

        return currentDate;
    }

    public static String getHour() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh");
        String currentDate = sdf.format(new Date());

        return currentDate;
    }

    public static String getMinute() {
        SimpleDateFormat sdf = new SimpleDateFormat("mm");
        String currentDate = sdf.format(new Date());

        return currentDate;
    }

    public static String getSeconds() {
        SimpleDateFormat sdf = new SimpleDateFormat("ss");
        String currentDate = sdf.format(new Date());

        return currentDate;
    }





}
