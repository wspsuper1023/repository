package com.paic.thread.demo.concurrent;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @Author: Admin
 * @Date: 2021/10/8 23:22
 * @Description:    Java并发包/并发队列课堂例子-03
 *      Semaphore   --  信号量 最多支持多少个资源访问
 */
public class Demo04 {
    public static void main(String[] args) {
        Semaphore wc = new Semaphore(3);    //假设只有3个茅坑
        for (int i = 1; i <= 10; i++) {     //有10个人需要上厕所
            new Parent("第"+i+"个人", wc).start();
        }
    }
}

class Parent extends Thread {
    private String name;
    private Semaphore wc;

    Parent(String name, Semaphore wc) {
        this.name = name;
        this.wc = wc;
    }

    @Override
    public void run() {
        try {
            if (wc.availablePermits() > 0) {
                System.out.println(name+"天助我也，有茅坑了，哈哈！！!");
            } else {
                System.out.println(name+"倒霉，怎么还没有茅坑555...");
            }

            wc.acquire();   //申请资源，如果资源达到3次就等待
            System.out.println(name+"嘿嘿，终于轮到我上厕所啦...");
            Thread.sleep(new Random().nextInt(1000));   //上厕所时间
            System.out.println(name+"上完厕所啦，爽......");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            wc.release();
        }
    }
}
