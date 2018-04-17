/**
 * Filename:     DanmakuTimeAligner.java
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

import com.cwgoover.danmu.android.Log;
import com.cwgoover.danmu.android.TextUtils;
import com.cwgoover.danmu.android.Utils;
import com.cwgoover.danmu.module.DanmakuAdvInfo;

import java.util.*;

public class DanmakuTimeAligner {

    private IDanmakuTimeStrategy mDanmakuTimeAligner;

    private List<DanmakuAdvInfo> mAdvInfos;

    public DanmakuTimeAligner(long videoDuration, List<DanmakuAdvInfo> danmakuAdvInfos) {
        mAdvInfos = danmakuAdvInfos;
        init(videoDuration, danmakuAdvInfos);
    }

    /**
     * 获取当前时间对应的弹幕SDK中的时间点，如果返回-1，获取失败，不能插入弹幕库
     * @param advId 广告id，如果为null代表正片时间点对应的弹幕SDK中的时间点
     * @param position 正片时间点，或者广告时间点（广告id非空）
     * @return 当前弹幕SDK中的时间点
     */
    public long getCurrentTime(String advId, long position) {
        if (mDanmakuTimeAligner == null) {
            return position;
        }
        long curTime;
        if (TextUtils.isEmpty(advId)) {
            curTime = mDanmakuTimeAligner.getVideoCurrentTime(position);
        } else {
            curTime = mDanmakuTimeAligner.getAdvCurrentTime(advId, position);
        }
        return curTime;
    }

    /**
     * 获取当前的正片时间点或者广告时间点，用于发送后端前进行时间校正。如果返回null，不发送后端
     * 如果Map中 ORIGIN_ADVID_KEY 为null，表示正片时间；否则是广告所属id对应的时间
     * @param position 当前弹幕SDK中的时间点
     * @return 当前的正片时间点或者广告时间点
     */
    public Map<String, String> getOriginalTime(long position) {
        if (mDanmakuTimeAligner == null) {
            return null;
        }
        return mDanmakuTimeAligner.getOriginalTime(position);
    }

    /**
     * 是否需要拉取广告弹幕
     * @param position 当前正片时间点
     * @return true为拉取广告弹幕；false不拉取
     */
    public boolean needGetAdvList(long position) {
        return mDanmakuTimeAligner != null && mDanmakuTimeAligner.needGetAdvList(position);
    }

    public List<DanmakuAdvInfo> getDanmakuAdvInfos() {
        if (mAdvInfos == null) {
            return new ArrayList<>(1);
        }
        return mAdvInfos;
    }

    public void reset(long videoDuration, List<DanmakuAdvInfo> danmakuAdvInfos) {
        mAdvInfos = danmakuAdvInfos;
        init(videoDuration, danmakuAdvInfos);
    }

    public void release() {
        if (mDanmakuTimeAligner != null) {
            mDanmakuTimeAligner.release();
            mDanmakuTimeAligner = null;
        }
        mAdvInfos = null;
    }

    private void init(long videoDuration, List<DanmakuAdvInfo> danmakuAdvInfos) {
        // 去掉danmakuAdvInfos里的无效数据
        List<DanmakuAdvInfo> infos = filterAdvInfos(videoDuration, danmakuAdvInfos);
        if (videoDuration <= 0 || Utils.checkListEmpty(infos)) {
            mDanmakuTimeAligner = new DefaultDanmakuTimeStrategy();
        } else {
            // FIXME: wait for Player's implements
//            long duration = calcuTotalVideoDuration(videoDuration, infos);
//            mDanmakuTimeAligner = new AdvDanmakuTimeStrategy(duration, infos);
            mDanmakuTimeAligner = new AdvDanmakuTimeStrategy(videoDuration, infos);
        }
    }

    private long calcuTotalVideoDuration(long duration, List<DanmakuAdvInfo> danmakuAdvInfos) {
        long videoDuration = duration;
        for (DanmakuAdvInfo info : danmakuAdvInfos) {
            if (info.getAdvDuration() > 0) {
                videoDuration -= info.getAdvDuration();
            }
        }
        return videoDuration;
    }

    private List<DanmakuAdvInfo> filterAdvInfos(long videoDuration, List<DanmakuAdvInfo> danmakuAdvInfos) {
        if (Utils.checkListEmpty(danmakuAdvInfos)) {
            Log.d("Aligner", "danmakuAdvInfos is null or empty, so return");
            return danmakuAdvInfos;
        }
        List<DanmakuAdvInfo> advInfos = new ArrayList<>(danmakuAdvInfos);
        Log.d("Aligner", "advInfos' size is " + advInfos.size());

        DanmakuAdvInfo preInfo = null;
        Iterator<DanmakuAdvInfo> iterator = advInfos.iterator();
        while (iterator.hasNext()) {
            DanmakuAdvInfo info = iterator.next();
            if (info == null || TextUtils.isEmpty(info.getAdvId()) || info.getAdvStartTime() < 0
                    || info.getAdvStartTime() > videoDuration || info.getAdvDuration() <= 0) {
                iterator.remove();
                continue;
            }
            // 如果前后广告的startTime是一样的，删除后面的广告数据
            if (preInfo != null && preInfo.getAdvStartTime() == info.getAdvStartTime()) {
                iterator.remove();
            }
            preInfo = info;
        }
        // 切割正片需要依赖广告的插入时间，所以这里按照广告起始时间排序
        if (!Utils.checkListEmpty(advInfos)) {
            advInfos.sort(new Comparator<DanmakuAdvInfo>() {
                @Override
                public int compare(DanmakuAdvInfo o1, DanmakuAdvInfo o2) {
                    long lhs = o1.getAdvStartTime();
                    long rhs = o2.getAdvStartTime();
                    // -1: less than, 1: greater than, 0: equal, all inverses for descending
                    return lhs > rhs ? 1 : (lhs < rhs) ? -1 : 0;
                }
            });
        }
        Log.d("Aligner", "advInfos' size is " + advInfos.size()
                + ", advInfos=" + advInfos.toString());
        return advInfos;
    }

}
