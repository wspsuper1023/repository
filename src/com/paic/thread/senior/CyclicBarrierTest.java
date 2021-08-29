package com.paic.thread.senior;

import java.util.concurrent.CyclicBarrier;

/**
 * @Author: Admin
 * @Date: 2021/1/31 22:14
 * @Description:    线程计数器
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        for (int i = 0; i < 5; i++) {
            new Writer(cyclicBarrier).start();
        }
    }
}

class Writer extends Thread {
    CyclicBarrier cyclicBarrier;
    public Writer(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"开始写入数据...");
        try {
            Thread.sleep(30);

            System.out.println(Thread.currentThread().getName()+"写入数据成功...");

            cyclicBarrier.await();  //如果有线程未执行完毕一直等待
            System.out.println(Thread.currentThread().getName()+"所有数据执行完毕...");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
