/**
 * Filename:     BlockingQueueOldThreadTest.java
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
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlockingQueueOldThreadTest {

    public static void main(String[] args) {
        List<Task> buffer = new ArrayList<>(Contants.MAX_BUFFTER_SIZE);
        ExecutorService service = Executors.newFixedThreadPool(Contants.NUM_OF_CONSUMER
                + Contants.NUM_OF_PRODUCER);
        for (int i = 1; i <= Contants.NUM_OF_PRODUCER; i++) {
            service.execute(new Producer(buffer));
        }
        for (int i = 1; i <= Contants.NUM_OF_CONSUMER; i++) {
            service.execute(new Consumer(buffer));
        }
    }

    static class Consumer implements Runnable {
        private final List<Task> buffer;

        Consumer(List<Task> buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (buffer) {
                    while (buffer.isEmpty()) {
                        try {
                            System.out.println("Consumer[" + Thread.currentThread().getName()
                                + "] waiting...");
                            buffer.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Task task = buffer.remove(0);
                    buffer.notifyAll();
                    System.out.println("Consumer[" + Thread.currentThread().getName()
                        + "] got " + task);
                }
            }
        }
    }

    static class Producer implements Runnable {
        private final List<Task> buffer;

        Producer(List<Task> buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (buffer) {
                    while (buffer.size() >= Contants.MAX_BUFFTER_SIZE) {
                        try {
                            System.out.println("Producer[" + Thread.currentThread().getName()
                                    + "] waiting...");
                            buffer.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Task task = new Task();
                    buffer.add(task);
                    buffer.notifyAll();
                    System.out.println("Producer[" + Thread.currentThread().getName()
                        + "] add " + task);
                }
            }
        }
    }
}

class Contants {
    static final int MAX_BUFFTER_SIZE = 10;
    static final int NUM_OF_PRODUCER = 2;
    static final int NUM_OF_CONSUMER = 3;
}

class Task {
    private String id;

    Task() {
        id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Task[" + id + "]";
    }
}