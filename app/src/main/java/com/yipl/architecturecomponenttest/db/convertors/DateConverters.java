package com.yipl.architecturecomponenttest.db.convertors;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by umesh on 11/15/17.
 */

public class DateConverters {

    @TypeConverter
    public static Date toDate(long date) {
        return date == Long.MIN_VALUE ? null : new Date(date);
    }

    @TypeConverter
    public static long fromDate(Date date) {
        return date == null ? Long.MIN_VALUE : date.getTime();
    }


}
