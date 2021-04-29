package com.demo.lock;

/**
 * 不可重入
 *
 * @author 杨滨
 * @date 2021年04月29日
 */
public class MyLock {
    private boolean isLocked = false;

    public synchronized void lock() throws InterruptedException {
        System.out.println("我加了一把锁");
        while (isLocked) {
            wait();
        }
        isLocked = true;
    }

    public synchronized void unlock() {
        isLocked = false;
        notify();
    }
}

class Count {
    public static void main(String[] args) throws InterruptedException {
        new Count().print();
    }

    MyLock lock = new MyLock();

    public void print() throws InterruptedException {
        lock.lock();
        doAdd();
        lock.unlock();
    }

    public void doAdd() throws InterruptedException {
        lock.lock();
        //do something
        System.out.println("do something");
        lock.unlock();
    }
}