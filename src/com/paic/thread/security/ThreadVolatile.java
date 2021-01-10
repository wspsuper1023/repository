package com.paic.thread.security;

/**
 * @Author: Admin
 * @Date: 2020/12/14 22:32
 * @Description: volatileˢ��ȫ�ֱ��������̣߳�������֤ԭ���ԡ��̲߳���ȫ��
 */
public class ThreadVolatile {

    public static void main(String[] args) throws InterruptedException {
        ThreadVolatileDemo threadVolatileDemo = new ThreadVolatileDemo();
        threadVolatileDemo.start();
        Thread.sleep(3000);

        //���߳��޸�ȫ�ֱ���Ϊfalse
        threadVolatileDemo.setFlag(false);

        System.out.println("flagֵ�Ѿ��޸�Ϊ��" + threadVolatileDemo.flag);
        Thread.sleep(1000);
        System.out.println("flagֵ�Ѿ��޸�Ϊ��" + threadVolatileDemo.flag);
    }



}

class ThreadVolatileDemo extends Thread {

    public volatile boolean flag = true;

    @Override
    public void run() {
        System.out.println("���߳̿�ʼִ��...");
        while (flag) {

        }
        System.out.println("���߳�ִ�н���...");
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

}
