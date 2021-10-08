package com.paic.thread.demo.concurrent;

import java.util.concurrent.CyclicBarrier;

/**
 * @Author: Admin
 * @Date: 2021/10/8 23:04
 * @Description:    Java������/�������п�������-03
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
        System.out.println("�߳�"+Thread.currentThread().getName()+"��ʼִ������...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("�߳�"+Thread.currentThread().getName()+"����ִ������...");

        try {
            cyclicBarrier.await();  //��5���̶߳�ִ�н����Ժ���ִ�к���Ĵ���
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"ȫ��ִ�����...");
    }
}
