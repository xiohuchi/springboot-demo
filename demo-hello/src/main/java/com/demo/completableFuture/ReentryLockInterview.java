package com.demo.completableFuture;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 杨滨
 * @date 2021年04月26日
 */
public class ReentryLockInterview {
    static int i = 0;


    public static void main(String[] args) {
        test2();
    }

    private static void test1() {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (i < 100) {
                    lock.lock();
                    System.out.println(Thread.currentThread().getId() + ":" + i++);
                    lock.unlock();
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (i < 100) {
                    lock.lock();
                    System.out.println(Thread.currentThread().getId() + ":" + i++);
                    lock.unlock();
                }
            }
        }.start();
    }

    public static void test2() {
        AtomicInteger i = new AtomicInteger();
        new Thread(() -> {
            while (i.get() < 10000) {
                while (i.get() % 2 == 0) {
                    System.out.println(Thread.currentThread().getName() + "----" + (i.get()));
                    i.getAndIncrement();
                }

            }
        }).start();

        new Thread(() -> {
            while (i.get() < 10000) {
                while (i.get() % 2 == 1) {
                    System.out.println(Thread.currentThread().getName() + "----" + (i.get()));
                    i.getAndIncrement();
                }
            }
        }).start();
    }
}
