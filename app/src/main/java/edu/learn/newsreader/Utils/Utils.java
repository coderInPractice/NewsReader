package edu.learn.newsreader.Utils;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static String DateFormat(String oldstringDate){
        String newDate;
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", new Locale(getCountry()));
        try {
            Date date = inputFormat.parse(oldstringDate);

            DateFormat outputFormat = new SimpleDateFormat("E ,dd MMM yyyy",new Locale("en","IN"));
            newDate = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            newDate = oldstringDate;
        }

        return newDate;
    }

    public static String getCountry(){
        Locale locale = new Locale("en","IN");
        String country = String.valueOf(locale.getCountry());
        return country.toLowerCase();
    }

    public static String DateToTimeFormat(String oldStringDate){
        PrettyTime p = new PrettyTime(new Locale("en","IN"));
        String isTime = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss",
                    Locale.ENGLISH);
            Date date = sdf.parse(oldStringDate);
            isTime = p.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return isTime;
    }
}
