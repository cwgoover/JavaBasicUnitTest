/**
 * Filename:     AdvDanmakuTimeStrategy.java
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

import com.cwgoover.danmu.android.DateUtils;
import com.cwgoover.danmu.android.Log;
import com.cwgoover.danmu.android.TextUtils;
import com.cwgoover.danmu.android.Utils;
import com.cwgoover.danmu.module.DanmakuAdvInfo;
import com.cwgoover.danmu.module.DanmakuVideoInfo;
import com.sun.istack.internal.NotNull; // FIXME: need to change

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdvDanmakuTimeStrategy implements IDanmakuTimeStrategy {

    private static final long GET_ADVLIST_MIN_TIME = DateUtils.MINUTE_IN_MILLIS;

    private List<DanmakuAdvInfo> mAdvInfos;

    private List<DanmakuVideoInfo> mVideoInfos;

    AdvDanmakuTimeStrategy(long videoDuration, @NotNull List<DanmakuAdvInfo> danmakuAdvInfos) {
        mAdvInfos = danmakuAdvInfos;
        init(videoDuration, danmakuAdvInfos);
    }

    /**
     * 获取广告在整个视频中的时间点，如果返回-1，广告信息错误，不能插入弹幕库
     * @param advId 广告id
     * @param position 广告播放的时间点（对应广告单独的时间轴）
     * @return 广告在整个视频中的时间点
     */
    @Override
    public long getAdvCurrentTime(String advId, long position) {
        if (Utils.checkListEmpty(mAdvInfos) || TextUtils.isEmpty(advId)) {
            return -1;
        }
        for (DanmakuAdvInfo adv : mAdvInfos) {
            if (TextUtils.equals(adv.getAdvId(), advId)) {
                if (position >= 0 && position <= adv.getAdvDuration()) {
                    return adv.getAdvOffsetStartTime() + position;
                }
            }
        }
        return -1;
    }

    /**
     * 获取正片在整个视频中的时间点，如果返回-1，正片信息错误，不能插入弹幕库
     * @param position 正片播放的时间点（对应正片的时间轴）
     * @return 正片在整个视频中的时间点
     */
    @Override
    public long getVideoCurrentTime(long position) {
        if (Utils.checkListEmpty(mVideoInfos)) {
            return -1;
        }
        for (DanmakuVideoInfo video : mVideoInfos) {
            if (position >= video.mStartTime && position <= (video.mStartTime + video.mDuration)) {
                return video.mOffsetStartTime - video.mStartTime + position;
            }
        }
        return -1;
    }

    @Override
    public Map<String, String> getOriginalTime(long position) {
        if (Utils.checkListEmpty(mVideoInfos) || Utils.checkListEmpty(mAdvInfos)) {
            return null;
        }

        for (DanmakuVideoInfo video : mVideoInfos) {
            if (position >= video.mOffsetStartTime && position <= (video.mOffsetStartTime + video.mDuration)) {
                Map<String, String> res = new HashMap<>();
                String time = String.valueOf(video.mStartTime - video.mOffsetStartTime + position);
                res.put(IDanmakuTimeStrategy.ORIGIN_TIME_KEY, time);
                return res;
            }
        }

        for (DanmakuAdvInfo adv : mAdvInfos) {
            if (position >= adv.getAdvOffsetStartTime()
                    && position <= (adv.getAdvOffsetStartTime() + adv.getAdvDuration())) {
                String time = String.valueOf(position - adv.getAdvOffsetStartTime());
                Map<String, String> res = new HashMap<>();
                res.put(IDanmakuTimeStrategy.ORIGIN_TIME_KEY, time);
                res.put(IDanmakuTimeStrategy.ORIGIN_ADVID_KEY, adv.getAdvId());
                return res;
            }
        }
        return null;
    }

    @Override
    public boolean needGetAdvList(long position) {
        if (Utils.checkListEmpty(mVideoInfos)) {
            return false;
        }
        for (DanmakuVideoInfo video : mVideoInfos) {
            if (video.mNextAdv == null) {
                continue;
            }
            if (Math.abs(video.mNextAdv.getAdvStartTime() - position) <= GET_ADVLIST_MIN_TIME) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void release() {
        mAdvInfos = null;
        mVideoInfos = null;
    }

    private void init(long videoDuration, List<DanmakuAdvInfo> danmakuAdvInfos) {
        mVideoInfos = new ArrayList<>();
        mVideoInfos.addAll(splitVideoWithAdv(videoDuration, danmakuAdvInfos));
        calculateEachOffsetStartTime();
        printListInfo();
    }

    private List<DanmakuVideoInfo> splitVideoWithAdv(long videoDuration, List<DanmakuAdvInfo> danmakuAdvInfos) {
        List<DanmakuVideoInfo> videoInfos = new ArrayList<>();
        boolean isFirstCut = true;
        DanmakuAdvInfo currentAdvInfo = null;
        for (int i = 0; i < danmakuAdvInfos.size(); i++) {
            DanmakuAdvInfo adv = danmakuAdvInfos.get(i);
            if (adv == null || TextUtils.isEmpty(adv.getAdvId())) {
                continue;
            }
            // 不切割正片的广告丢弃
            if (adv.getAdvStartTime() <= 0 || adv.getAdvStartTime() == videoDuration) {
                continue;
            }

            if (isFirstCut || i == 0) {
                DanmakuVideoInfo video = new DanmakuVideoInfo();
                video.mVid = i;
                video.mStartTime = 0;
                video.mDuration = adv.getAdvStartTime();
                video.mNextAdv = adv;
                videoInfos.add(video);
                isFirstCut = false;
            } else {
                DanmakuVideoInfo video = new DanmakuVideoInfo();
                video.mVid = i;
                video.mNextAdv = adv;
                video.mStartTime = danmakuAdvInfos.get(i-1).getAdvStartTime();
                video.mDuration = adv.getAdvStartTime() - video.mStartTime;
                videoInfos.add(video);
            }
            currentAdvInfo = adv;
        }
        int size = videoInfos.size();
        if (currentAdvInfo != null && size > 0 && (videoInfos.get(size - 1).mStartTime
                + videoInfos.get(size - 1).mDuration) < videoDuration) {
            DanmakuVideoInfo video = new DanmakuVideoInfo();
            video.mVid = size + 1;
            video.mNextAdv = null;
            video.mStartTime = currentAdvInfo.getAdvStartTime();
            video.mDuration = videoDuration - video.mStartTime;
            videoInfos.add(video);
        }
        if (danmakuAdvInfos.get(danmakuAdvInfos.size() - 1).getAdvStartTime() == videoDuration
                && videoInfos.size() > 0) {
            videoInfos.get(videoInfos.size() - 1).mNextAdv = danmakuAdvInfos.get(danmakuAdvInfos.size() - 1);
        }
        return videoInfos;
    }

    private void calculateEachOffsetStartTime() {
        int videoSize = mVideoInfos.size();
        int advSize = mAdvInfos.size();
        int size = Math.min(videoSize, advSize);
        for (int i = 0; i < size; i++) {
            if (mAdvInfos.get(0).getAdvStartTime() == 0) {
                // 如果广告插在正片最前面
                if (i == 0) {
                    mAdvInfos.get(i).setAdvOffsetStartTime(mAdvInfos.get(i).getAdvStartTime());
                } else {
                    mAdvInfos.get(i).setAdvOffsetStartTime(mVideoInfos.get(i-1).mOffsetStartTime
                            + mVideoInfos.get(i-1).mDuration);
                }
                mVideoInfos.get(i).mOffsetStartTime = mAdvInfos.get(i).getAdvOffsetStartTime()
                        + mAdvInfos.get(i).getAdvDuration();
            } else {
                // 最开始是正片
                if (i == 0) {
                    mVideoInfos.get(i).mOffsetStartTime = mVideoInfos.get(i).mStartTime;
                } else {
                    mVideoInfos.get(i).mOffsetStartTime = mAdvInfos.get(i-1).getAdvOffsetStartTime()
                            + mAdvInfos.get(i-1).getAdvDuration();
                }
                mAdvInfos.get(i).setAdvOffsetStartTime(mVideoInfos.get(i).mOffsetStartTime
                        + mVideoInfos.get(i).mDuration);
            }
        }

        int remainItemCount = Math.abs(videoSize - advSize);
        if (videoSize == 0 || remainItemCount > 1 || remainItemCount == 0) {
            if (remainItemCount > 1) {
                Log.e("calculateEachOffsetStartTime: remainItemCount(" + remainItemCount
                        + "), so splitting error!!! mVideoInfos=" + mVideoInfos.toString()
                        + ", mAdvInfos=" + mAdvInfos.toString());
            }
            return;
        }
        if (videoSize > advSize) {
            mVideoInfos.get(videoSize - 1).mOffsetStartTime = mAdvInfos.get(advSize - 1).getAdvOffsetStartTime()
                    + mAdvInfos.get(advSize - 1).getAdvDuration();
        } else {
            mAdvInfos.get(advSize - 1).setAdvOffsetStartTime(mVideoInfos.get(videoSize - 1).mOffsetStartTime
                    + mVideoInfos.get(videoSize - 1).mDuration);
        }
    }

    private void printListInfo() {
        Log.d("---", "print Adv info:");
        for (DanmakuAdvInfo adv : mAdvInfos) {
            Log.d("  ", "adv: " + adv.toString());
        }

        Log.d("---", "print Video info:");
        for (DanmakuVideoInfo video : mVideoInfos) {
            Log.d("  ", "Video: " + video.toString());
        }
    }

}
