package com.demo.lock;

/**
 * @author 杨滨
 * @date 2021年04月29日
 */
public class MyLock2 {

    boolean isLocked = false;
    Thread lockedBy = null;
    int lockedCount = 0;

    public synchronized void lock() throws InterruptedException {
        Thread thread = Thread.currentThread();
        while (isLocked && lockedBy != thread) {
            wait();
        }
        isLocked = true;
        lockedCount++;
        lockedBy = thread;
    }

    public synchronized void unlock() {
        if (Thread.currentThread() == this.lockedBy) {
            lockedCount--;
            if (lockedCount == 0) {
                isLocked = false;
                notify();
            }
        }
    }
}

class Count2 {
    public static void main(String[] args) throws InterruptedException {
        new Count2().print();
    }

    MyLock2 lock = new MyLock2();

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