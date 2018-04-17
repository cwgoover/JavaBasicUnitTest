/**
 * Filename:     AdvBeforeWithTail.java
 * Description:
 * Author:       CAO Ting
 * Version:      1.0
 * Create at:    21/09/2017
 * Modification History:
 * Date             Author        Version     Description
 * ------------------------------------------------------------------
 * 21/09/2017        caozangzang     1.0       1.0 Version
 */
package com.cwgoover.danmu.testdata;

import com.cwgoover.danmu.android.Utils;
import com.cwgoover.danmu.module.DanmakuAdvInfo;

import java.util.ArrayList;
import java.util.List;

public class AdvBeforeWithTail implements IData {
    @Override
    public List<DanmakuAdvInfo> createAdvList() {
        List<DanmakuAdvInfo> advInfos = new ArrayList<>();
        advInfos.add(Utils.createAdvInfo("0", 0, 10));
        advInfos.add(Utils.createAdvInfo("1", 10, 5));
        advInfos.add(Utils.createAdvInfo("2", 20, 4));
        advInfos.add(Utils.createAdvInfo("3", 30, 8));
        advInfos.add(Utils.createAdvInfo("4", 50, 9));
        advInfos.add(Utils.createAdvInfo("5", 100, 9));
        return advInfos;
    }
}
