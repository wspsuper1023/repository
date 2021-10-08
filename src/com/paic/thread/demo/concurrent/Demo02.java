package com.paic.thread.demo.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: Admin
 * @Date: 2021/10/8 22:32
 * @Description: Java������/�������п�������-02
 *
 *  CountDownLatch
 */
public class Demo02 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("���̣߳�"+Thread.currentThread().getName()+"��ʼִ��");
                try {
                    countDownLatch.countDown();
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("���̣߳�"+Thread.currentThread().getName()+"����ִ��");
            }


        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("���̣߳�"+Thread.currentThread().getName()+"��ʼִ��");
                try {
                    countDownLatch.countDown();
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("���̣߳�"+Thread.currentThread().getName()+"����ִ��");
            }
        }).start();

        countDownLatch.await(); //ֻ�е�countDownLatch��Ϊ0ʱ�����̲߳Żᱻ����
        System.out.println("���߳̿�ʼִ������......");
        for (int i = 0; i < 10; i++) {
            System.out.println("main, i="+i);
        }
    }
}
