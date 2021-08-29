package com.paic.thread.senior;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @Author: Admin
 * @Date: 2021/1/31 22:25
 * @Description:    计数信号量【10个人抢3个茅坑】
 */
public class SemaphoreTest {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 10; i++) {
            new Parent(semaphore, "第"+i+"个人,").start();
        }
    }
}

class Parent extends Thread {
    private Semaphore wc;
    private String name;

    public Parent(Semaphore wc, String name) {
        this.wc = wc;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            //获取到资源，减一
            int availablePermits = wc.availablePermits();
            if (availablePermits > 0) {
                System.out.println(name + "天助我也，我终于有茅坑啦...");
            } else {
                System.out.println(name + "怎么又没茅坑啦...");
            }

            wc.acquire();
            System.out.println(name + "我终于能上厕所了，爽!!!");

            Thread.sleep(new Random().nextInt(1000));   //模拟上厕所时间

            System.out.println(name + "厕所终于上完了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            wc.release();   //释放茅坑
        }
    }
}
