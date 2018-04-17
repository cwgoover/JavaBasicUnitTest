/**
 * Filename:     Utils.java
 * Description:
 * Author:       CAO Ting
 * Version:      1.0
 * Create at:    19/09/2017
 * Modification History:
 * Date             Author        Version     Description
 * ------------------------------------------------------------------
 * 19/09/2017        caozangzang     1.0       1.0 Version
 */
package com.cwgoover.danmu.android;

import com.cwgoover.danmu.module.DanmakuAdvInfo;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Utils {

    public static boolean checkListEmpty(List<?> list){
        return list == null || list.isEmpty();
    }

    public static DanmakuAdvInfo createAdvInfo(String advId, long time, long duration) {
        DanmakuAdvInfo adv = new DanmakuAdvInfo();
        adv.setAdvId(advId);
        adv.setAdvStartTime(time);
        adv.setAdvDuration(duration);
        return adv;
    }

    public static String showTime(long millisecond) {
        StringBuilder builder = new StringBuilder();
        long minutes = millisecond / DateUtils.MINUTE_IN_MILLIS;
        long seconds = millisecond % DateUtils.MINUTE_IN_MILLIS / DateUtils.SECOND_IN_MILLIS;
        builder.append(minutes);
        builder.append(":");
        builder.append(seconds);
        return builder.toString();
    }

    // https://stackoverflow.com/questions/9027317/how-to-convert-milliseconds-to-hhmmss-format
    // https://stackoverflow.com/questions/625433/how-to-convert-milliseconds-to-x-mins-x-seconds-in-java
    /**
     * int seconds = (int) (milliseconds / 1000) % 60 ;
     * int minutes = (int) ((milliseconds / (1000*60)) % 60);
     * int hours   = (int) ((milliseconds / (1000*60*60)) % 24);
     */
    public static String showTimeUnit(long millis) {
//        return String.format("%1$tH:%1$tM:%1$tS", millis);
        return  String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
    }
}
