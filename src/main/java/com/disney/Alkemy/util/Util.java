package com.disney.alkemy.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    
    public static Date stringToDate(String fetchString) throws ParseException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date fecha = format.parse(fetchString);
        return fecha;
    }
    
    public static String dateToString(Date fetch) throws ParseException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String fecha = format.format(fetch);
        return fecha;
    }
}