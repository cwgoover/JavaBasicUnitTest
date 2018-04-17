/**
 * Filename:     MainTest.java
 * Description:
 * Author:       CAO Ting
 * Version:      1.0
 * Create at:    19/09/2017
 * Modification History:
 * Date             Author        Version     Description
 * ------------------------------------------------------------------
 * 19/09/2017        caozangzang     1.0       1.0 Version
 */
package com.cwgoover.danmu;

import com.cwgoover.danmu.android.Log;
import com.cwgoover.danmu.android.TextUtils;
import com.cwgoover.danmu.module.DanmakuAdvInfo;
import com.cwgoover.danmu.testdata.*;
import com.cwgoover.danmu.time.DanmakuTimeAligner;
import com.cwgoover.danmu.time.IDanmakuTimeStrategy;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MainTest {

    public static void main(String[] args) {
        MainTest main = new MainTest();
        main.init();
    }

    private void init() {
        Scanner sc = new Scanner(System.in);
        List<DanmakuAdvInfo> list = prepareAdvList();
        DanmakuTimeAligner aligner = new DanmakuTimeAligner(100, list);
        System.out.println("\n\ngetDanmakuAdvInfos: " + aligner.getDanmakuAdvInfos().toString());

        Log.d("\n\n\n : 测试开始请回车：");
        while (!"exit".equals(sc.nextLine())) {
            Log.d("获取当前时间请输入1，获取原始时间请输入2：");
            String type = sc.nextLine();
            Log.d("请输入时间点：");
            String time = sc.nextLine();

            if ("1".equals(type)) {
                Log.d("希望获取广告时间请输入广告id，否则回车：");
                String advId = sc.nextLine();

                if (!TextUtils.isEmpty(advId)) {
                    System.out.println("getCurrentTime(adv:" + advId + "-" + time + ")="
                            + aligner.getCurrentTime(advId, Long.parseLong(time)));
                } else {
                    System.out.println("getCurrentTime(" + time + ")=" + aligner.getCurrentTime(null, Long.parseLong(time)));
                }
            } else if ("2".equals(type)) {
                Map<String, String> map = aligner.getOriginalTime(Long.parseLong(time));
                if (map != null) {
                    String id = map.get(IDanmakuTimeStrategy.ORIGIN_ADVID_KEY);
                    String t = map.get(IDanmakuTimeStrategy.ORIGIN_TIME_KEY);
                    System.out.println("getOriginalTime: id=" + (id == null ? "null" : id) + ", time=" + t);
                } else {
                    System.out.println("getOriginalTime: input time(" + time + ") error");
                }
            }

            System.out.println();
            Log.d("测试开始请回车：");
        }
    }

    private List<DanmakuAdvInfo> prepareAdvList() {
        IData testData1 = new AdvAfterWithoutTail();
        IData testData2 = new AdvAfterWithTail();
        IData testData3 = new AdvBeforeWithouTail();
        IData testData4 = new AdvBeforeWithTail();
        IData testInvalid = new InvalidAdvData();
//        return testData4.createAdvList();
//        return testInvalid.createAdvList();
        return null;
    }
}
