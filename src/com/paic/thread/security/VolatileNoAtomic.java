package com.paic.thread.security;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Admin
 * @Date: 2020/12/14 23:01
 * @Description:    AtomicInteger保证数据原子性，线程安全
 */
public class VolatileNoAtomic extends Thread {
    //需要10个线程同时共享 static修饰的的变量存放在静态区，只会存一次，所有线程都共享
    //private volatile static int count = 0;
    private static AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            count.incrementAndGet();
        }
        System.out.println(getName() + "," + count.get());
    }

    public static void main(String[] args) {
        //创建10个线程
        VolatileNoAtomic[] volatileNoAtomicList = new VolatileNoAtomic[10];
        for (int i = 0; i < volatileNoAtomicList.length; i++) {
            volatileNoAtomicList[i] = new VolatileNoAtomic();
        }

        for (int i = 0; i < volatileNoAtomicList.length; i++) {
            volatileNoAtomicList[i].start();
        }
    }
}
