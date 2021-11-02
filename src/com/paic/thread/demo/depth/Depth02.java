package com.paic.thread.demo.depth;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Admin
 * @Date: 2021/10/31 10:02
 * @Description: 重入锁ReentrantLock
 */
public class Depth02 extends Thread {
    Lock lock = new ReentrantLock();    //效果同synchronized一样，如果不是冲入锁，不同函数之间相互调可能会造成死锁

    private void get() {
        lock.lock();
        System.out.println(Thread.currentThread().getName()+",get");
        set();
        lock.unlock();
    }

    private void set() {
        lock.lock();
        System.out.println(Thread.currentThread().getName()+",set");
        lock.unlock();
    }

    @Override
    public void run() {
        get();
    }

    public static void main(String[] args) {
        Depth02 depth02 = new Depth02();

        new Thread(depth02).start();
        new Thread(depth02).start();
        new Thread(depth02).start();
        new Thread(depth02).start();
        new Thread(depth02).start();
    }
}
