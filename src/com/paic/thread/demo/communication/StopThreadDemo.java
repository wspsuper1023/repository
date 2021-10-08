package com.paic.thread.demo.communication;

/**
 * @Author: Admin
 * @Date: 2021/10/7 22:02
 * @Description: �̼߳�ͨѶ��������03���߳�ֹͣ
 * ��Ҫ˼·��
 * ��1��ʹ���˳���־
 * ��2��ʹ��interrupt�ж�
 *  �׳��߳��쳣interrupt�������쳣ʱ���ý�ֹͣ��־��Ϊfalse;����ʹ��stop��������Ϊstop��������ع����̲߳���ȫ
 */
public class StopThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        StopThread stopThread = new StopThread();
        stopThread.start();

        for (int i = 0; i < 10; i++) {
            System.out.println("�������߳�:i=" + i);
            Thread.sleep(2000);
            if (i == 6) {
                stopThread.interrupt(); //���쳣
            }
        }
    }
}

class StopThread extends Thread {
    private volatile boolean flag = true;   //�ɼ���

    @Override
    public synchronized void run() {
        System.out.println("���߳̿�ʼ����...");

        while (flag) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
                stopMethod();
            }
        }

        System.out.println("���߳̽�������...");
    }

    private void stopMethod() {
        this.flag = false;
        System.out.println("�Ѿ���flag�޸�Ϊ:"+flag);
    }
}
