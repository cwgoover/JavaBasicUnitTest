/**
 * Filename:     CopyOnWriteArrayListTest.java
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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CopyOnWriteArrayList通过增加写时复制语义来避免并发访问引起的问题，也就是说任何修改操作都会在底层创建一个列表的副本，
 * 也就意味着之前已有的迭代器不会碰到意料之外的修改。
 * 这种方式对于不要严格读写同步的场景非常有用，因为它提供了更好的性能。
 * 记住，要尽量减少锁的使用，因为那势必带来性能的下降，CopyOnWriteArrayList很明显也是通过牺牲空间获得了时间
 */
public class CopyOnWriteArrayListTest {

    private static final int THREAD_POOL_SIZE = 2;

    public static void main(String[] args) {
        // java.lang.ArrayIndexOutOfBoundsException
        List<Double> list = new ArrayList<>();
//        List<Double> list = new CopyOnWriteArrayList<>();

        ExecutorService service = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        System.out.println("run #1 runnable...");
        service.execute(new AddRunnable(list));
        System.out.println("run #2 runnable...");
        service.execute(new AddRunnable(list));
        service.shutdown();
    }

    static class AddRunnable implements Runnable {
        private List<Double> list;

        AddRunnable(List<Double> list) {
            this.list = list;
        }

        @Override
        public void run() {
            for (int i =0; i < 10000; ++i) {
                list.add(Math.random());
            }
        }
    }
}
