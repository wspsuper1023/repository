package com.paic.thread.demo.concurrent;

import java.util.concurrent.CyclicBarrier;

/**
 * @Author: Admin
 * @Date: 2021/10/8 23:04
 * @Description:    Java并发包/并发队列课堂例子-03
 *  CyclicBarrier
 */
public class Demo03 {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        for (int i = 0; i < 5; i++) {
            new Writer(cyclicBarrier).start();
        }
    }
}

class Writer extends Thread {
    private CyclicBarrier cyclicBarrier;

    Writer(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        System.out.println("线程"+Thread.currentThread().getName()+"开始执行任务...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程"+Thread.currentThread().getName()+"结束执行任务...");

        try {
            cyclicBarrier.await();  //等5个线程都执行结束以后再执行后面的代码
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"全部执行完毕...");
    }
}
