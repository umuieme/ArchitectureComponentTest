package com.yipl.architecturecomponenttest.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by umesh on 11/17/17.
 */

public class AppDateUtils {

    public static final String DEFAULT_PATTERN = "MMM dd, YYYY";

    public static String convertDate(Date date) {
        if (date == null) return "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_PATTERN);
        return simpleDateFormat.format(date);
    }

    public static Date convertDate(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_PATTERN);
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
