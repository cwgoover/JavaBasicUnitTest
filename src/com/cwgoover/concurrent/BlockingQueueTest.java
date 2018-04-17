/**
 * Filename:     BlockingQueueTest.java
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

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class BlockingQueueTest {

    public static void main(String[] args) {
        BlockingQueue<Task> buffer = new LinkedBlockingDeque<>(Contants.MAX_BUFFTER_SIZE);

        ExecutorService es = Executors.newFixedThreadPool(Contants.NUM_OF_CONSUMER + Contants.NUM_OF_PRODUCER);
        for(int i = 1; i <= Contants.NUM_OF_PRODUCER; ++i) {
            es.execute(new Producer(buffer));
        }
        for(int i = 1; i <= Contants.NUM_OF_CONSUMER; ++i) {
            es.execute(new Consumer(buffer));
        }
    }

    static class Consumer implements Runnable {
        private BlockingQueue<Task> buffer;

        Consumer(BlockingQueue<Task> buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Task task = buffer.take();
                    System.out.println("Consumer[" + Thread.currentThread().getName()
                            + "] got " + task);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Producer implements Runnable {
        private BlockingQueue<Task> buffer;

        Producer(BlockingQueue<Task> buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Task task = new Task();
                    buffer.put(task);
                    System.out.println("Producer[" + Thread.currentThread().getName()
                            + "] put " + task);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
