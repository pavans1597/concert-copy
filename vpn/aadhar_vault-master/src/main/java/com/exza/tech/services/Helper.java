package com.exza.tech.services;

import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public    class Helper {

    public static Date convertStringToDate(String strformat, String strdate){
        SimpleDateFormat format=new SimpleDateFormat(strformat);
        DateTime date=null;
        try{
            date=new DateTime(format.parse(strdate));
        }catch (ParseException e) {
            System.out.println("Error in converting date==>"+e.getMessage());
        }
        return date.toDate();
    }
}
