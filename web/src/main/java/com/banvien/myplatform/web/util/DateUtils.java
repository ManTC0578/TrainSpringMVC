package com.banvien.myplatform.web.util;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static long HOUR = 3600000L;
    public static final Date addDate(Date d, int days) {
        Date res = new Date(d.getTime() + days * 24 * 3600000L);
        return res;
    }

    public static final String date2String(Date d, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(d);
    }

    public static int getWeekOfYear(Timestamp d) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(d.getTime()));

        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public static int getMonthOfYear(Timestamp d) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(d.getTime()));

        return calendar.get(Calendar.MONTH);

    }

    public static Timestamp move2TheEndOfDay(Timestamp input) {
		Timestamp res = null;
		if (input != null) {
			res = new Timestamp(input.getTime() + 24 * HOUR - 1000);
		}
		return res;
	}
}
