package com.paic.thread.demo.concurrent;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @Author: Admin
 * @Date: 2021/10/8 23:22
 * @Description:    Java������/�������п�������-03
 *      Semaphore   --  �ź��� ���֧�ֶ��ٸ���Դ����
 */
public class Demo04 {
    public static void main(String[] args) {
        Semaphore wc = new Semaphore(3);    //����ֻ��3��é��
        for (int i = 1; i <= 10; i++) {     //��10������Ҫ�ϲ���
            new Parent("��"+i+"����", wc).start();
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
                System.out.println(name+"������Ҳ����é���ˣ���������!");
            } else {
                System.out.println(name+"��ù����ô��û��é��555...");
            }

            wc.acquire();   //������Դ�������Դ�ﵽ3�ξ͵ȴ�
            System.out.println(name+"�ٺ٣������ֵ����ϲ�����...");
            Thread.sleep(new Random().nextInt(1000));   //�ϲ���ʱ��
            System.out.println(name+"�����������ˬ......");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            wc.release();
        }
    }
}
