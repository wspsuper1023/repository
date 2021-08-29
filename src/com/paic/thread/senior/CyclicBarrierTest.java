package com.paic.thread.senior;

import java.util.concurrent.CyclicBarrier;

/**
 * @Author: Admin
 * @Date: 2021/1/31 22:14
 * @Description:    �̼߳�����
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
        System.out.println(Thread.currentThread().getName()+"��ʼд������...");
        try {
            Thread.sleep(30);

            System.out.println(Thread.currentThread().getName()+"д�����ݳɹ�...");

            cyclicBarrier.await();  //������߳�δִ�����һֱ�ȴ�
            System.out.println(Thread.currentThread().getName()+"��������ִ�����...");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
