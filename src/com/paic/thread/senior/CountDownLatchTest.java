package com.paic.thread.senior;

import java.util.concurrent.CountDownLatch;

import static java.lang.Thread.sleep;

/**
 * @Author: Admin
 * @Date: 2021/1/31 21:55
 * @Description:    计数器
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(0);

        System.out.println("我是主线程...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程1开始执行任务......");
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown(); //每次减一
                System.out.println("子线程1执行任务结束......");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程2开始执行任务......");
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                System.out.println("子线程2执行任务结束......");
            }
        }).start();

        countDownLatch.await(); //如果不为0，一直等待
        System.out.println("主线程开始执行...");
        for (int i = 0; i < 10; i++) {
            System.out.println("main i:"+i);
        }
    }
}
