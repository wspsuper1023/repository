package com.paic.thread.senior;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @Author: Admin
 * @Date: 2021/1/31 22:25
 * @Description:    �����ź�����10������3��é�ӡ�
 */
public class SemaphoreTest {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 10; i++) {
            new Parent(semaphore, "��"+i+"����,").start();
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
            //��ȡ����Դ����һ
            int availablePermits = wc.availablePermits();
            if (availablePermits > 0) {
                System.out.println(name + "������Ҳ����������é����...");
            } else {
                System.out.println(name + "��ô��ûé����...");
            }

            wc.acquire();
            System.out.println(name + "���������ϲ����ˣ�ˬ!!!");

            Thread.sleep(new Random().nextInt(1000));   //ģ���ϲ���ʱ��

            System.out.println(name + "��������������");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            wc.release();   //�ͷ�é��
        }
    }
}
