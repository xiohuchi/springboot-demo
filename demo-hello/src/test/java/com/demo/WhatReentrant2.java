package com.demo;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示可重入锁是什么意思
 *
 * @author 杨滨
 * @date 2021年04月22日
 */
public class WhatReentrant2 {

    public static void main(String[] args) {
        final ReentrantLock lock = new ReentrantLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    System.out.println("第1次获取锁，这个锁是：" + lock);

                    int index = 1;
                    while (true) {
                        try {
                            lock.lock();
                            System.out.println("第" + (++index) + "次获取锁，这个锁是：" + lock);

                            try {
                                Thread.sleep(new Random().nextInt(200));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            if (index == 10) {
                                break;
                            }
                        } finally {
                            lock.unlock();
                        }

                    }

                } finally {
                    lock.unlock();
                }
            }
        }).start();
    }
}
