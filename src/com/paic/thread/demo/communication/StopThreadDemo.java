package com.paic.thread.demo.communication;

/**
 * @Author: Admin
 * @Date: 2021/10/7 22:02
 * @Description: 线程间通讯课堂例子03：线程停止
 * 主要思路：
 * （1）使用退出标志
 * （2）使用interrupt中断
 *  抛出线程异常interrupt，捕获异常时调用将停止标志改为false;不能使用stop方法，因为stop方法不会回滚，线程不安全
 */
public class StopThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        StopThread stopThread = new StopThread();
        stopThread.start();

        for (int i = 0; i < 10; i++) {
            System.out.println("我是主线程:i=" + i);
            Thread.sleep(2000);
            if (i == 6) {
                stopThread.interrupt(); //抛异常
            }
        }
    }
}

class StopThread extends Thread {
    private volatile boolean flag = true;   //可见性

    @Override
    public synchronized void run() {
        System.out.println("子线程开始运行...");

        while (flag) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
                stopMethod();
            }
        }

        System.out.println("子线程结束运行...");
    }

    private void stopMethod() {
        this.flag = false;
        System.out.println("已经将flag修改为:"+flag);
    }
}
