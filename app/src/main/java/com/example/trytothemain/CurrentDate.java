package com.example.trytothemain;

import java.util.Calendar;

public class CurrentDate {
    public static String currentDate(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH); // Note: Month starts from 0 (January is 0)
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String currentDate = String.valueOf(day) + ".0" + String.valueOf(month+1) + "." + String.valueOf(year);
        return currentDate;
    }
}
