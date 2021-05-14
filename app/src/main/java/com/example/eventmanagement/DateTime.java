package com.example.eventmanagement;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {
    private String CURRENT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS";
    private String NEW_DATE_FORMAT = "dd.MM.yyyy.";

    public DateTime() {
    }

    public String getDate(String datetimeString){
        SimpleDateFormat sdf = new SimpleDateFormat(CURRENT_DATE_FORMAT);
        DateFormat newFormat = new SimpleDateFormat(NEW_DATE_FORMAT);
        Date d = null;
        String strDate = "";
        try {
            d = sdf.parse(datetimeString);
            strDate = newFormat.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return strDate;
    }
}