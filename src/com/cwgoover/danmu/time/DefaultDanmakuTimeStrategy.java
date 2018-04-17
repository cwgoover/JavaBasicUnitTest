/**
 * Filename:     DefaultDanmakuTimeStrategy.java
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

import java.util.HashMap;
import java.util.Map;

public class DefaultDanmakuTimeStrategy implements IDanmakuTimeStrategy {

    @Override
    public long getAdvCurrentTime(String advId, long position) {
        return 0;
    }

    @Override
    public long getVideoCurrentTime(long position) {
        return position;
    }

    @Override
    public Map<String, String> getOriginalTime(long position) {
        Map<String, String> res = new HashMap<>();
        res.put(IDanmakuTimeStrategy.ORIGIN_TIME_KEY, String.valueOf(position));
        return res;
    }

    @Override
    public boolean needGetAdvList(long position) {
        return false;
    }

    @Override
    public void release() {
    }
}
