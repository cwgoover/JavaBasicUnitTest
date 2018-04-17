/**
 * Filename:     DanmakuAdvInfo.java
 * Description:
 * Author:       CAO Ting
 * Version:      1.0
 * Create at:    19/09/2017
 * Modification History:
 * Date             Author        Version     Description
 * ------------------------------------------------------------------
 * 19/09/2017        caozangzang     1.0       1.0 Version
 */
package com.cwgoover.danmu.module;

public class DanmakuAdvInfo {
    /** 广告id */
    private String advId;
    /** 广告插入正片的时间点 */
    private long advStartTime;
    /** 广告时长 */
    private long advDuration;
    /** 广告在混合视频中插播的时间点 */
    private long advOffsetStartTime;

    public void setAdvId(String advId) {
        this.advId = advId;
    }

    public void setAdvStartTime(long advStartTime) {
        this.advStartTime = advStartTime;
    }

    public void setAdvDuration(long advDuration) {
        this.advDuration = advDuration;
    }

    public void setAdvOffsetStartTime(long advOffsetTime) {
        this.advOffsetStartTime = advOffsetTime;
    }

    public String getAdvId() {
        return advId;
    }

    public long getAdvStartTime() {
        return advStartTime;
    }

    public long getAdvDuration() {
        return advDuration;
    }

    public long getAdvOffsetStartTime() {
        return advOffsetStartTime;
    }

    @Override
    public String toString() {
        return "DanmakuAdvInfo{ advId=" + advId
                + ", advStartTime=" + advStartTime
                + ", advDuration=" + advDuration
                + ", advOffsetStartTime=" + advOffsetStartTime
                + " }";
    }
}
