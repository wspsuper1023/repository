package com.paic.thread.demo.depth;

/**
 * @Author: Admin
 * @Date: 2021/10/31 9:51
 * @Description: 锁的深度化01：重入锁ReentrantLock，synchronized：也叫递归锁，指同一线程，外层函数获取该锁以后，内层仍能获取该锁
 */
public class Depth01 extends Thread {

    private synchronized void get() {
        System.out.println(Thread.currentThread().getName()+",get");
        set();
    }

    private synchronized void set() {
        System.out.println(Thread.currentThread().getName()+",set");
    }

    @Override
    public void run() {
        get();
    }

    public static void main(String[] args) {
        Depth01 depth01 = new Depth01();

        new Thread(depth01).start();
        new Thread(depth01).start();
        new Thread(depth01).start();
        new Thread(depth01).start();
        new Thread(depth01).start();
    }
}
