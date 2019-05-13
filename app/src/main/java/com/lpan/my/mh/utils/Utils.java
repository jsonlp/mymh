package com.lpan.my.mh.utils;

import android.os.Build;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

/**
 * Created by lpan on 2017/2/8.
 */

public class Utils {

    public static final long ONE_KB = 1024;

    private static Pattern mMentionPattern = Pattern.compile("\\{@[a-z0-9\\-_]{22}\\}", Pattern.CASE_INSENSITIVE);

    public static final SimpleDateFormat HHmm = new SimpleDateFormat("HH:mm");

    /**
     * 转换毫秒数成“分、秒”，如“01:53”。若超过60分钟则显示“时、分、秒”，如“01:01:30
     *
     * @param
     */
    public static String converLongTimeToStr(long time) {
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;

        long hour = (time) / hh;
        long minute = (time - hour * hh) / mi;
        long second = (time - hour * hh - minute * mi) / ss;

        String strHour = hour < 10 ? "0" + hour : "" + hour;
        String strMinute = minute < 10 ? "0" + minute : "" + minute;
        String strSecond = second < 10 ? "0" + second : "" + second;
        if (hour > 0) {
            return strHour + ":" + strMinute + ":" + strSecond;
        } else {
            return strMinute + ":" + strSecond;
        }
    }

    public static String formatTime(long time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String str = dateFormat.format(time);
        return str;
    }

    public static double formatSizeToKB(long size) {
        double d = (double) size / ONE_KB;
        return (double) Math.round(d * 100) / 100;
    }

    public static double formatSizeToMB(long size) {
        double d = (double) size / ONE_KB / ONE_KB;
        return (double) Math.round(d * 100) / 100;
    }

    public static double formatSizeToGB(long size) {
        double d = (double) size / ONE_KB / ONE_KB / ONE_KB;
        return (double) Math.round(d * 100) / 100;
    }


    public static boolean hasGingerbread() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
    }

    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    public static boolean hasHoneycombMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1;
    }

    public static boolean hasJellyBeanMR2() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2;
    }

    public static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    public static boolean hasKitkat() {
        return Build.VERSION.SDK_INT >= 19/*Build.VERSION_CODES.KITKAT*/;
    }

    public static boolean hasIceCreamSandwich() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }

    public static boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static byte[] toByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int n = 0;
        while (-1 != (n = inputStream.read(buffer))) {
            output.write(buffer, 0, n);
        }
        return output.toByteArray();
    }

    public static double keepTwoDecimal(double input){
        return (double) Math.round(input * 100) / 100;
    }
}
