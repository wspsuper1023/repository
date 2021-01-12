package com.paic.thread.basic;

/**
 * @Author: Admin
 * @Date: 2021/1/11 22:38
 * @Description:    ֹͣ�߳�
 */
public class StopThread {
    public static void main(String[] args) throws InterruptedException {
        StopThreadDemo stopThreadDemo = new StopThreadDemo();
        stopThreadDemo.start();

        for (int i = 0; i < 10; i++) {
            System.out.println("�������߳�,i: "+i);
            Thread.sleep(1000);

            if (i == 3) {
                //��ǰ�ȴ����߳��׳��쳣
                stopThreadDemo.interrupt();
            }
        }
    }
}

class StopThreadDemo extends Thread {
    private boolean flag = true;

    @Override
    public void run() {
        System.out.println("���߳̿�ʼִ��...");
        while (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                stopThread();
            }
        }
        System.out.println("���߳̽���ִ��...");
    }

    public void stopThread() {
        System.out.println("����stopThread����");
        this.flag = false;
        System.out.println("flag���޸�Ϊ: "+flag);
    }
}
