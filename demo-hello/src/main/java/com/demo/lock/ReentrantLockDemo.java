package com.demo.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 设置公平锁
 *
 * @author 杨滨
 * @date 2021年04月27日
 */
public class ReentrantLockDemo implements Runnable {
    private static ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " get lock");
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo rtld = new ReentrantLockDemo();
        Thread thread1 = new Thread(rtld);
        Thread thread2 = new Thread(rtld);
        thread1.start();
        thread2.start();
    }
}
