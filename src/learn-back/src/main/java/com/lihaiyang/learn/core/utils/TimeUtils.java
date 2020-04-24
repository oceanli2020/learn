package com.lihaiyang.learn.core.utils;

public class TimeUtils {

    public static String formatDateTime(long mss) {
        String DateTimes = null;
        long days = mss / (60 * 60 * 24);
        long hours = (mss % (60 * 60 * 24)) / (60 * 60);
        long minutes = (mss % (60 * 60)) / 60;
        long seconds = mss % 60;
        if (days > 0) {
            DateTimes = days + "day" + hours + "h" + minutes + "min" +seconds + "s";
        } else if (hours > 0) {
            DateTimes = hours + "h" + minutes + "min" +seconds + "s";
        } else if (minutes > 0) {
            DateTimes = minutes + "min" +seconds + "s";
        } else {
            DateTimes = seconds + "s";
        }
        return DateTimes;
    }

}
