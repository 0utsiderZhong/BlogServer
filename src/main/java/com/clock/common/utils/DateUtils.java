package com.clock.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @className: DateUtils
 * @description: 时间工具类
 * @author: Clock
 * 
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 获取当前Date型日期
     *
     * @return Date() 当前日期
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     *
     * @return String
     */
    public static String getDate() {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static final String getTime() {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static final String dateTimeNow() {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static final String dateTimeNow(final String format) {
        return parseDateToStr(format, new Date());
    }

    public static final String dateTime(final Date date) {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static final String parseDateToStr(final String format, final Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    public static final Date dateTime(final String format, final String ts) {
        try {
            return new SimpleDateFormat(format).parse(ts);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static final String datePath() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20180808
     */
    public static final String dateTime() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    /**
     * 获取过去 <code>intervals</code>时间的字符串数组
     *
     * @param intervals 过去时间
     * @return 字符串数组
     */
    public static List<String> getPastDaysList(int intervals) {
        List<String> pastDaysList = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YYYY_MM_DD);
        for (int i = intervals - 1; i >= 0; i--) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - i);
            Date today = calendar.getTime();
            pastDaysList.add(simpleDateFormat.format(today));
        }
        return pastDaysList;
    }

    /**
     * 获取过去anchor开始到anchor+intervals时间段的日期
     *
     * @param anchor    锚点
     * @param intervals 间隔
     * @return 时间段日期String
     */
    public static List<String> getPastDaysList(int anchor, int intervals) {
        List<String> pastDaysList = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YYYY_MM_DD);
        for (int i = intervals - 1; i >= 0; i--) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - anchor - i);
            Date today = calendar.getTime();
            pastDaysList.add(simpleDateFormat.format(today));
        }
        return pastDaysList;
    }

    /**
     * 根据传入的时间判断是刚刚，几秒前等
     *
     * @param paramTime 需要判断的时间
     * @return 判断出的结果
     */
    public static String showTime(Date paramTime) {
        String result = "";
        if (paramTime == null) {
            return result;
        }
        //当前时间的时间戳
        long nowTimeLong = System.currentTimeMillis();
        //传入的时间的时间戳
        long paramTimeLong = paramTime.getTime();

        long resultLong = Math.abs(nowTimeLong - paramTimeLong);

        //一分钟内
        if (resultLong < 60000) {
            long seconds = resultLong / 1000;
            //如果是5秒内
            if (seconds < 5) {
                result = "刚刚";
            } else {
                result = seconds + "秒前";
            }
        }//一小时内
        else if (resultLong >= 60000 && resultLong < 3600000) {
            long seconds = resultLong / 60000;
            result = seconds + "分钟前";
        }// 一天内
        else if (resultLong >= 3600000 && resultLong < 86400000) {
            long seconds = resultLong / 3600000;
            result = seconds + "小时前";
        }// 三十天内
        else if (resultLong >= 86400000 && resultLong < 1702967296) {
            long seconds = resultLong / 86400000;
            result = seconds + "天前";
        } else {
            result = new SimpleDateFormat(YYYY_MM_DD).format(paramTime);
        }
        return result;
    }
}
