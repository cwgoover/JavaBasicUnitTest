/**
 * Filename:     CountDownLatchTest.java
 * Description:
 * Author:       CAO Ting
 * Version:      1.0
 * Create at:    18/03/2018
 * Modification History:
 * Date             Author        Version     Description
 * ------------------------------------------------------------------
 * 18/03/2018        caozangzang     1.0       1.0 Version
 */
package com.cwgoover.concurrent;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    private static final int MAX_WORK_DURATION = 5000;
    private static final int MIN_WORK_DURATION = 1000;

    public static void main(String[] args) {
        // 创建倒计时闩并指定倒计时次数为2
        CountDownLatch latch = new CountDownLatch(2);
        Worker w1 = new Worker("骆昊", getRandomWorkDuration(MIN_WORK_DURATION, MAX_WORK_DURATION));
        Worker w2 = new Worker("王大锤", getRandomWorkDuration(MIN_WORK_DURATION, MAX_WORK_DURATION));

        new Thread(new WorkRunnable(w1, latch)).start();
        new Thread(new WorkRunnable(w2, latch)).start();

        try {
            // 等待倒计时闩减到0
            latch.await();
            System.out.println("All jobs have been finished!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static long getRandomWorkDuration(long min, long max) {
        return (long) (Math.random() * (max - min) + min);
    }

    // 使用静态内部类保证在main方法中可以正常创建对象
    static class Worker {
        private String name;
        // 工作持续时间
        private long workDuration;

        Worker(String name, long workDuration) {
            this.name = name;
            this.workDuration = workDuration;
        }

        void doWork() {
            System.out.println(name + " begins to work, duration=" + (workDuration / 1000));
            try {
                // 用休眠模拟工作执行的时间
                Thread.sleep(workDuration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " has finished the job...");
        }
    }

    static class WorkRunnable implements Runnable {

        private Worker worker;
        private CountDownLatch cdLatch;

        WorkRunnable(Worker worker, CountDownLatch latch) {
            this.worker = worker;
            this.cdLatch = latch;
        }

        @Override
        public void run() {
            // 让工人开始工作
            worker.doWork();
            // 工作完成后倒计时次数减1
            cdLatch.countDown();
        }
    }
}
