package com.dss.sframework.factories;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gustavo.vieira on 07/05/2015.
 */
public class DateFactory {

    public String getCompleteData() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
        String currentDate = sdf.format(new Date());

        return currentDate;
    }

    public String getCompleteDataYY() {
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd hh-mm-ss");
        String currentDate = sdf.format(new Date());

        return currentDate;
    }

    public String getyyyyMMdd(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());

        return currentDate;
    }

    public String getddMMyyy(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String currentDate = sdf.format(new Date());

        return currentDate;
    }

    public String getMMyyyydd(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM-yyyy-dd");
        String currentDate = sdf.format(new Date());

        return currentDate;
    }

    public String getyyMMdd() {
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        String currentDate = sdf.format(new Date());

        return currentDate;
    }

    public String getddMMyy() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
        String currentDate = sdf.format(new Date());

        return currentDate;
    }

    public String MMyydd() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-yy-dd");
        String currentDate = sdf.format(new Date());

        return currentDate;
    }



    public String gethhmmss() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh-mm-ss");
        String currentDate = sdf.format(new Date());

        return currentDate;
    }

    public String getYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String currentDate = sdf.format(new Date());

        return currentDate;
    }

    public String getYearYY() {
        SimpleDateFormat sdf = new SimpleDateFormat("yy");
        String currentDate = sdf.format(new Date());

        return currentDate;
    }

    public String getMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        String currentDate = sdf.format(new Date());

        return currentDate;
    }


    public String getDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        String currentDate = sdf.format(new Date());

        return currentDate;
    }

    public String getHour() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh");
        String currentDate = sdf.format(new Date());

        return currentDate;
    }

    public String getMinute() {
        SimpleDateFormat sdf = new SimpleDateFormat("mm");
        String currentDate = sdf.format(new Date());

        return currentDate;
    }

    public String getSeconds() {
        SimpleDateFormat sdf = new SimpleDateFormat("ss");
        String currentDate = sdf.format(new Date());

        return currentDate;
    }





}
