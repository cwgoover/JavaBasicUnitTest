/**
 * Filename:     IDanmakuTimeStrategy.java
 * Description:
 * Author:       CAO Ting
 * Version:      1.0
 * Create at:    19/09/2017
 * Modification History:
 * Date             Author        Version     Description
 * ------------------------------------------------------------------
 * 19/09/2017        caozangzang     1.0       1.0 Version
 */
package com.cwgoover.danmu.time;

import java.util.Map;

public interface IDanmakuTimeStrategy {

    String ORIGIN_TIME_KEY = "time";
    String ORIGIN_ADVID_KEY = "advid";

    /**
     * 获取广告在整个视频中的时间点，如果返回-1，广告信息错误，不能插入弹幕库
     * @param advId 广告id
     * @param position 广告播放的时间点（对应广告单独的时间轴）
     * @return 广告在整个视频中的时间点
     */
    long getAdvCurrentTime(String advId, long position);

    /**
     * 获取正片在整个视频中的时间点，如果返回-1，正片信息错误，不能插入弹幕库
     * @param position 正片播放的时间点（对应正片的时间轴）
     * @return 正片在整个视频中的时间点
     */
    long getVideoCurrentTime(long position);

    /**
     * 获取原始时间，如果正片或广告信息错误，返回为null
     * 其中正片时间对应的 ORIGIN_ADVID_KEY 为null
     * @param position 弹幕SDK中的时间点
     * @return 正片或者广告时间点，如果是广告时间带id
     */
    Map<String, String> getOriginalTime(long position);

    /**
     * 是否需要拉取广告弹幕
     * @param position 当前正片时间点
     * @return true为拉取广告弹幕；false不拉取
     */
    boolean needGetAdvList(long position);

    void release();
}
