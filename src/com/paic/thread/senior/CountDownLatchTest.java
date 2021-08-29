package com.paic.thread.senior;

import java.util.concurrent.CountDownLatch;

import static java.lang.Thread.sleep;

/**
 * @Author: Admin
 * @Date: 2021/1/31 21:55
 * @Description:    ������
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(0);

        System.out.println("�������߳�...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("���߳�1��ʼִ������......");
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown(); //ÿ�μ�һ
                System.out.println("���߳�1ִ���������......");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("���߳�2��ʼִ������......");
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                System.out.println("���߳�2ִ���������......");
            }
        }).start();

        countDownLatch.await(); //�����Ϊ0��һֱ�ȴ�
        System.out.println("���߳̿�ʼִ��...");
        for (int i = 0; i < 10; i++) {
            System.out.println("main i:"+i);
        }
    }
}
