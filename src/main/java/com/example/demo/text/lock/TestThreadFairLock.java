package com.example.demo.text.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author HouJianJun
 * @description: 公平锁测试
 * @date 2024/5/13 10:57
 */
public class TestThreadFairLock {
    private ReentrantLock reentrantLock = new ReentrantLock(true);
    private void testLock() {
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(runnable);
            thread.setName("线程" + (i + 1));
            thread.start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " 启动了，准备获取锁");
                reentrantLock.lock();
                System.out.println(Thread.currentThread().getName() + " 获取了锁");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        }
    };

    public static void main(String args[]) {
        TestThreadFairLock testThread = new TestThreadFairLock();
        testThread.testLock();
    }
}

