/**
 * Filename:     Log.java
 * Description:
 * Author:       CAO Ting
 * Version:      1.0
 * Create at:    20/09/2017
 * Modification History:
 * Date             Author        Version     Description
 * ------------------------------------------------------------------
 * 20/09/2017        caozangzang     1.0       1.0 Version
 */
package com.cwgoover.danmu.android;

public class Log {

    public static void d(String msg) {
        System.out.println(" : " + msg);
    }

    public static void d(String tag, String msg) {
        System.out.println(tag + ": " + msg);
    }

    public static void e(String tag, String msg) {
        System.out.println(tag + ": " + msg);
    }

    public static void e (String msg) {
        System.out.println(" : " + msg);
    }

}
