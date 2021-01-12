package com.paic.thread.basic;

/**
 * @Author: Admin
 * @Date: 2021/1/11 22:38
 * @Description:    停止线程
 */
public class StopThread {
    public static void main(String[] args) throws InterruptedException {
        StopThreadDemo stopThreadDemo = new StopThreadDemo();
        stopThreadDemo.start();

        for (int i = 0; i < 10; i++) {
            System.out.println("我是主线程,i: "+i);
            Thread.sleep(1000);

            if (i == 3) {
                //当前等待的线程抛出异常
                stopThreadDemo.interrupt();
            }
        }
    }
}

class StopThreadDemo extends Thread {
    private boolean flag = true;

    @Override
    public void run() {
        System.out.println("子线程开始执行...");
        while (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                stopThread();
            }
        }
        System.out.println("子线程结束执行...");
    }

    public void stopThread() {
        System.out.println("调用stopThread方法");
        this.flag = false;
        System.out.println("flag被修改为: "+flag);
    }
}
