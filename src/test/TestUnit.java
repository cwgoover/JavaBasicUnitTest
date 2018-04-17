/**
 * Filename:     TestUnit.java
 * Description:
 * Author:       CAO Ting
 * Version:      1.0
 * Create at:    08/11/2017
 * Modification History:
 * Date             Author        Version     Description
 * ------------------------------------------------------------------
 * 08/11/2017        caozangzang     1.0       1.0 Version
 */
package test;

import java.util.ArrayList;
import java.util.List;

public class TestUnit {

    public static void main(String[] args) {
        TestUnit unit = new TestUnit();
        unit.testArrayListCrash();
//        unit.testConcurrentModificationException();
    }

    private void testArrayListCrash() {
        ArrayList<Integer> list = new ArrayList<>();
        int i = 0;
        list.add(i);

        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 1;
                while (true) {
                    if (list.contains(i)) {
//                        System.out.println("Thread #1: contains" + i + ", so return");
                        continue;
                    }
                    list.add(i);
                    System.out.println("Thread #1: " + i);
                    i++;
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 3;
                while (true) {
                    if (list.contains(i)) {
//                        System.out.println("Thread #2: contains" + i + ", so return");
                        continue;
                    }
                    list.add(i);
                    System.out.println("Thread #2: add " + i);
                    i++;
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 1;
                while (true) {
                    if (list.contains(i)) {
//                        System.out.println("Thread #2: contains" + i + ", so return");
                        continue;
                    }
                    list.add(i);
                    System.out.println("Thread #3: add " + i);
                    i++;
                }
            }
        }).start();

        while (true) {
            if (list.contains(i)) {
//                System.out.println("Main Thread: contains" + i + ", so return");
                continue;
            }
            list.add(i);
            System.out.println("Main Thread: add " + i);
            i++;
        }
    }

    private List<Integer> mPendingListMin;

    private void testConcurrentModificationException() {
        Thread.currentThread().setName("Main Thread");

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    getPendingDanmuList();
                }
            }
        }, "thread#1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    getPendingDanmuList();
                }
            }
        }, "thread#2").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    getPendingDanmuList();
                }
            }
        }).start();

        int i = 0;
        while (true) {
            addPendingList(i);
            i++;
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void getPendingDanmuList() {
        if (!checkListEmpty(mPendingListMin)) {
            for (int pendingStartMin : mPendingListMin) {
                Log.d("get: pendingStartMin=" + pendingStartMin);
            }
            mPendingListMin.clear();
        }
    }

    private synchronized void addPendingList(int startMinute) {
        if (mPendingListMin == null) {
            mPendingListMin = new ArrayList<>();
        }
        if (!mPendingListMin.contains(startMinute)) {
            Log.d("add: startMinute=" + startMinute);
            mPendingListMin.add(startMinute);
        }
    }

    public static boolean checkListEmpty(List<?> list){
        return list == null || list.isEmpty();
    }

}
