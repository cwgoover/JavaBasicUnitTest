/**
 * Filename:     ConcurrentTest.java
 * Description:
 * Author:       CAO Ting
 * Version:      1.0
 * Create at:    18/03/2018
 * Modification History:
 * Date             Author        Version     Description
 * ------------------------------------------------------------------
 * 18/03/2018        caozangzang     1.0       1.0 Version
 */
package test;

import java.util.ArrayList;
import java.util.List;

public class ConcurrentTest {

    private final Object mLock = new Object();
    private final List<String> mList = new ArrayList<>();

    public static void main(String[] args) {
        ConcurrentTest concurrent = new ConcurrentTest();
        concurrent.main();
        concurrent.method(1);
        concurrent.method(2);
    }

    private void main() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (mLock) {
                    for (int i = 0; i < 500; i++) {
                        System.out.println("main: add item: " + i);
                        mList.add("index:" + i);
                    }
                }
            }
        }).start();
    }

    private void method(int index) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (mLock) {
                    System.out.println("\nthread #" + index + ": loop");
                    for (String item : mList) {
                        System.out.println("#"+ index +": mList->" + item);
                    }
                }
            }
        }).start();
    }
}
