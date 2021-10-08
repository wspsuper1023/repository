package com.paic.thread.demo.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: Admin
 * @Date: 2021/10/8 22:32
 * @Description: Java并发包/并发队列课堂例子-02
 *
 *  CountDownLatch
 */
public class Demo02 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程，"+Thread.currentThread().getName()+"开始执行");
                try {
                    countDownLatch.countDown();
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("子线程，"+Thread.currentThread().getName()+"结束执行");
            }


        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程，"+Thread.currentThread().getName()+"开始执行");
                try {
                    countDownLatch.countDown();
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("子线程，"+Thread.currentThread().getName()+"结束执行");
            }
        }).start();

        countDownLatch.await(); //只有当countDownLatch变为0时，主线程才会被唤醒
        System.out.println("主线程开始执行任务......");
        for (int i = 0; i < 10; i++) {
            System.out.println("main, i="+i);
        }
    }
}
