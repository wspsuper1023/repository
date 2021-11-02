package com.paic.thread.demo.depth;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: Admin
 * @Date: 2021/11/2 23:10
 * @Description: 自旋锁
 */
public class SpinLock {
    private AtomicReference<Thread> sign = new AtomicReference<>();

    public void lock() {
        Thread current = Thread.currentThread();
        while (!sign.compareAndSet(null, current)) {}
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        sign.compareAndSet(current, null);
    }
}

class Test implements Runnable {
    static int sum;
    private SpinLock lock;

    public Test(SpinLock lock) {
        this.lock = lock;
    }

    public static void main(String[] args) throws InterruptedException {
        SpinLock lock = new SpinLock();
        for (int i = 0; i < 100; i++) {
            Test test = new Test(lock);
            Thread t = new Thread(test);
            t.start();
        }

        Thread.currentThread().sleep(1000);
        System.out.println(sum);
    }

    @Override
    public void run() {
        this.lock.lock();
        this.lock.lock();
        sum++;
        this.lock.unlock();
        this.lock.unlock();
    }
}
