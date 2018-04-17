/**
 * Filename:     DanmakuVideoInfo.java
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

public class DanmakuVideoInfo {
    /** 分割正片的id */
    public int mVid;
    /** 分割正片的开始时间点 */
    public long mStartTime;
    /** 分割正片在混合视频中的开始时间点 */
    public long mOffsetStartTime;
    /** 分割正片的时长 */
    public long mDuration;
    /** 下一段广告信息 */
    public DanmakuAdvInfo mNextAdv;

    @Override
    public String toString() {
        return "DanmakuVideoInfo{ mVid=" + mVid
                + ", mStartTime=" + mStartTime
                + ", mDuration=" + mDuration
                + ", mOffsetStartTime=" + mOffsetStartTime
//                + ", mNextAdv=" + (mNextAdv == null ? "null" : mNextAdv.toString())
                + ", mNextAdv=" + (mNextAdv == null ? "null" : mNextAdv.getAdvId())
                + " }";
    }
}
