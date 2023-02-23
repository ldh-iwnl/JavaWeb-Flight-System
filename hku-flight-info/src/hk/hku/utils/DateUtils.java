package hk.hku.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;


public class DateUtils {
    /**
     * 预定的日期格式
     */
    public static final String[] DATEFORMAT = {"yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyy年MM月dd日HH时mm分ss秒", "yyyy-MM-dd", "yyyy/MM/dd", "yy-MM-dd", "yy/MM/dd", "yyyy年MM月dd日", "HH:mm:ss",
            "yyyyMMddHHmmss", "yyyyMMdd", "yyyy.MM.dd", "yy.MM.dd", "yyyyMMddHHmmssSSS", "yyyy-MM-dd HH:mm:ss:SSS", "yyyy-MM-dd HH:mm:ss.SSS", "yyyy", "yyyyMM", "yyyyMMdd HH", "yyyyMMdd HH:mm",
            "yyyyMMdd HH:mm:ss", "yyyy-MM" };

    /**
     * 线程绑定的日期格式转换器缓存
     */
    private static final ThreadLocal<Map<String, SimpleDateFormat>> DATEFORMATERSCACHE = new ThreadLocal<Map<String, SimpleDateFormat>>();

    /**
     * 获取当前系统时间
     */
    public static Calendar getSystemCurrentTime() {
        final Calendar currentTime = Calendar.getInstance();
        return currentTime;
    }

    /**
     * 获取当前系统时间
     */
    public static String getSystemCurrentTime(int format) {
        return toDateStrByFormatIndex(getSystemCurrentTime(), format);
    }

    /**
     * 获取系统当前date类型时间
     */
    public static Date getCurrentDate() {
        return new Date();
    }

    /**
     * 获取系统当前日期和时间，格式为yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentDateTime() {
        return getFormatCurrentDate("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 返回格式化的当前日期/时间
     */
    public static String getFormatCurrentDate(String strFormat) {
        return msFormatDateTime(getCurrentDate(), strFormat);
    }

    /**
     * 日期/时间格式化显示（年、月、日、时、分、秒、毫秒、星期）
     */
    public static String msFormatDateTime(Date dtmDate, String strFormat) {

        if (strFormat.equals("")) {
            strFormat = "yyyy-MM-dd HH:mm:ss";
        }

        final SimpleDateFormat myFormat = new SimpleDateFormat(strFormat);

        return myFormat.format(dtmDate.getTime());
    }

    /**
     * 日期/时间格式化显示（年、月、日）
     */
    public static String msFormatDate(Date dtmDate, String strFormat) {

        if (strFormat.equals("")) {
            strFormat = "yyyy-MM-dd";
        }

        final SimpleDateFormat myFormat = new SimpleDateFormat(strFormat);

        return myFormat.format(dtmDate.getTime());
    }

    /**
     * 获取当前系统时间
     */
    public static String getSystemCurrentTime(String format) {
        return toDateStrByFormat(getSystemCurrentTime(), format);
    }

    // ======================日期转字符串基础格式化方法======================================================================================

    /**
     * @name 中文名称
     */
    private static SimpleDateFormat getDateFormater(String format) {
        Map<String, SimpleDateFormat> dateFormaters = DATEFORMATERSCACHE.get();
        SimpleDateFormat dateFormater = null;
        boolean formatersIsNull = false;

        if (dateFormaters == null) {
            dateFormaters = new HashMap<String, SimpleDateFormat>(3, 0.2f);
            DATEFORMATERSCACHE.set(dateFormaters);
            formatersIsNull = true;
        }
        dateFormater = dateFormaters.get(format);
        if (formatersIsNull || dateFormater == null) {
            dateFormater = new SimpleDateFormat(format);
            dateFormaters.put(format, dateFormater);
        }

        return dateFormater;
    }


    private static SimpleDateFormat getDateFormater(int format) {
        return getDateFormater(DATEFORMAT[format]);
    }

    // ======================日期转字符串基础方法======================================================================================

    /**
     *
     * Calendar日期转指定日期格式的字符串
     */
    public static String toDateStrByFormat(Calendar date, String format) {
        if (date == null) {
            return null;
        }
        return getDateFormater(format).format(date.getTime());
    }

    /**
     *
     * Calendar日期转指定日期格式的字符串
     */
    public static String toDateStrByFormatIndex(Calendar date, int format) {
        return toDateStrByFormat(date, DATEFORMAT[format]);
    }

    /**
     * java.util.Date日期转指定日期格式的字符串
     */
    public static String toDateStrByFormat(Date date, String format) {
        if (date == null) {
            return null;
        }
        return getDateFormater(format).format(date.getTime());
    }

    /**
     * java.util.Date日期转指定格式的字符串
     */
    public static String toDateStrByFormatIndex(Date date, int format) {
        return toDateStrByFormat(date, DATEFORMAT[format]);
    }

    // ======================日期转字符串方法======================================================================================

    /**
     * Calendar日期转日期字符串
     */
    public static String toDateTimeStr(Calendar date) {
        return toDateStrByFormatIndex(date, 0);
    }

    /**
     * Calendar日期转指定日期格式的字符串
     */
    public static String toDateTimeStr(int format, Calendar date) {
        return toDateStrByFormatIndex(date, format);
    }

    /**
     * Calendar日期转日期字符串
     */
    public static String toDateStr(Calendar date) {
        return toDateStrByFormatIndex(date, 3);
    }

    /**
     * java.util.Date日期转指定格式的日期字符串
     */
    public static String dateToString(Date date, int format) {
        return toDateStrByFormatIndex(date, format);
    }

    /**
     * java.util.Date日期转日期字符串
     */
    public static String dateToString(Date date) {
        return toDateStrByFormatIndex(date, 3);
    }

    // ======================xx转Date方法======================================================================================

    /**
     * Calendar日期转java.util.Date日期
     */
    public static Date convertCalendarToDate(Calendar c) {
        final Date d = new Date();
        d.setTime(c.getTimeInMillis());
        return d;
    }

    /**
     * 日期字符串转java.util.Date日期
     */
    public static Date stringToDate(String dateStr) throws Exception {
        return parseDate(dateStr, 3);
    }

    /**
     * 日期字符串转指定格式的java.util.Date日期
     */
    public static Date parseDate(String dateStr, int format) throws Exception {
        if (dateStr == null || dateStr.length() == 0) {
            return null;
        }

        try {
            return getDateFormater(format).parse(dateStr);
        } catch (ParseException ex) {
            return parseDate(dateStr, format + 1);
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new Exception("UT-07001:日志字符串" + dateStr + "格式不支持", ex);
        }
    }

    // ======================xx转Calendar方法======================================================================================

    /**
     * java.util.Date转Calendar
     */
    public static Calendar convUtilDateToUtilCalendar(Date date) {
        if (date == null) {
            return null;
        }

        final GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(date.getTime());

        return gc;
    }

    /**
     * java.sql.Timestamp转Calendar
     */
    public static Calendar convSqlTimestampToUtilCalendar(Timestamp date) {
        if (date == null) {
            return null;
        }
        final GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(date.getTime());
        return gc;
    }

    /**
     * 日期字符串转Calendar
     */
    public static Calendar parseDate(String dateStr) throws Exception {
        final Date result = parseDate(dateStr, 0);
        Calendar cal = null;

        if (result != null) {
            cal = new GregorianCalendar(0, 0, 0, 0, 0, 0);
            cal.setTime(result);
        }

        return cal;
    }

    // ======================日期转Timestamp方法======================================================================================

    /**
     * java.util.Date转java.sql.Timestamp
     */
    public static Timestamp convUtilDateToSqlTimestamp(Date date) {
        if (date == null) {
            return null;
        }
        return new Timestamp(date.getTime());
    }

    /**
     * Calendar日期对象转Timestamp日期对象
     */
    public static Timestamp convUtilCalendarToSqlTimestamp(Calendar date) {
        if (date == null) {
            return null;
        }
        return new Timestamp(date.getTimeInMillis());
    }

    /**
     * Calendar日期对象转Timestamp日期对象
     */
    public static Timestamp parseTimestamp(Calendar calendar) {
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 日期字符串转java.sql.Timestamp
     */
    public static Timestamp parseTimestamp(String dateStr) throws Exception {
        try {
            return new Timestamp(getDateFormater(3).parse(dateStr).getTime());
        } catch (ParseException ex) {
            throw new Exception("UT-07001:日志字符串" + dateStr + "格式不正确，格式:" + DATEFORMAT[3], ex);
        }
    }

    /**
     * 根据指定日期格式，日期字符串转java.sql.Timestamp
     */
    public static Timestamp parseTimestamp(String dateStr, int format) throws Exception {
        try {
            return new Timestamp(getDateFormater(format).parse(dateStr).getTime());
        } catch (ParseException ex) {
            throw new Exception("UT-07001:日志字符串" + dateStr + "格式不支持", ex);
        }
    }

    // ======================日期计算方法======================================================================================

    /**
     * 获取两个Calendar日期对象的天数差
     */
    public static int calendarMinus(Calendar d1, Calendar d2) {
        if (d1 == null || d2 == null) {
            return 0;
        }

        d1.set(11, 0);
        d1.set(12, 0);
        d1.set(13, 0);
        d1.set(14, 0);

        d2.set(11, 0);
        d2.set(12, 0);
        d2.set(13, 0);
        d2.set(14, 0);

        long t1 = d1.getTimeInMillis();
        long t2 = d2.getTimeInMillis();
        final long daylong = 86400000L;
        t1 -= t1 % daylong;
        t2 -= t2 % daylong;

        final long t = t1 - t2;
        final int value = (int) (t / daylong);

        return value;
    }

    /**
     * 获取两个Calendar日期对象的天数差
     */
    public static long calendarminus(Calendar d1, Calendar d2) {
        if (d1 == null || d2 == null) {
            return 0L;
        }
        return (d1.getTimeInMillis() - d2.getTimeInMillis()) / 86400000L;
    }

    /**
     * 给定任意日期Calendar对象，计算所在月天数
     */
    public static int calcMonthDays(Calendar date) {
        final Calendar t1 = (Calendar) date.clone();
        final Calendar t2 = (Calendar) date.clone();
        final int year = date.get(1);
        final int month = date.get(2);
        t1.set(year, month, 1);
        t2.set(year, month + 1, 1);
        t2.add(6, -1);
        return calendarMinus(t2, t1) + 1;
    }

    private static int calcDays(long t1, long t2) {
        final long millis = t1 - t2;
        if (millis == 0) {
            return 0;
        }
        return (int) (millis / (24 * 3600 * 1000));
    }

    /**
     * 获取两个Calendar日期对象的天数差
     */
    public static int calcDays(Calendar c1, Calendar c2) {
        return calcDays(c1.getTimeInMillis(), c2.getTimeInMillis());
    }

    /**
     * 获取两个java.util.Date日期对象的天数差
     */
    public static int calcDays(Date d1, Date d2) {
        return calcDays(d1.getTime(), d2.getTime());
    }

    /**
     * 给定任意日期Calendar对象，计算所在月的最后一天
     */
    public static Calendar lastDay(Calendar c) {
        final Calendar t = (Calendar) c.clone();
        t.set(Calendar.DAY_OF_MONTH, 1);
        t.add(Calendar.MONTH, 1);
        t.add(Calendar.DAY_OF_MONTH, -1);
        return t;
    }

    /**
     * 给定任意日期字符串，计算所在月的最后一天
     */
    public static Calendar lastDay(String dateStr) throws Exception {
        final Calendar t = parseDate(dateStr);
        t.set(Calendar.DAY_OF_MONTH, 1);
        t.add(Calendar.MONTH, 1);
        t.add(Calendar.DAY_OF_MONTH, -1);
        return t;

    }

    /**
     * 给定任意日期，计算所在季的季起日期和季终日期
     */
    public static Calendar[] calcAQuarter(Calendar day) {
        final Calendar[] quarter = new Calendar[2];

        int month = 0;
        quarter[0] = (Calendar) day.clone();
        month = quarter[0].get(Calendar.MONTH);

        if (month < 3) {
            month = 0;
        } else if (month < 6) {
            month = 3;
        } else if (month < 9) {
            month = 6;
        } else {
            month = 9;
        }

        quarter[0].set(Calendar.MONTH, month);
        quarter[0].set(Calendar.DAY_OF_MONTH, 1);

        quarter[1] = (Calendar) quarter[0].clone();
        quarter[1].set(Calendar.MONTH, month + 2);
        quarter[1] = lastDay(quarter[1]);

        return quarter;
    }

    /**
     * 获取年、月、日、时、分、秒、毫秒
     */
    public static int[] getYearMonthDayHH24MiMM(Calendar calendar) {
        return new int[] {calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
                calendar.get(Calendar.SECOND), calendar.get(Calendar.MILLISECOND) };
    }

    /**
     * 获取年、月、日、时、分、秒、毫秒
     */
    public static int[] getYearMonthDayHH24MiMM(Date date) {
        final Calendar calendar = getSystemCurrentTime();
        calendar.setTime(date);
        return getYearMonthDayHH24MiMM(calendar);
    }

    /**
     * 好微妙转毫秒
     */
    public static long nsToMs(long nsTime) {
        return nsTime / 1000000;
    }

}
