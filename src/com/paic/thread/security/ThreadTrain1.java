package com.paic.thread.security;

/**
* @Author: WUSHIPING
* @Date:   2020/12/13 23:04
* @Description: 同步代码块单元测试
*/
public class ThreadTrain1 implements  Runnable {

    private int trainCount = 100;
    private Object obj = new Object();

    @Override
    public void run() {
        while (trainCount>0) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sale();
        }
    }

    private void sale() {
        synchronized (obj) {//只有拿到🔒的线程才能访问【同步代码块】
            if (trainCount > 0) {
                System.out.println(Thread.currentThread().getName()+",出售第"+(100-trainCount+1)+"张票");
                trainCount--;
            }
        }
    }

    public static class ThreadDemo {
        public static void main(String[] args) {
            ThreadTrain1 threadTrain1 = new ThreadTrain1();
            Thread t1 = new Thread(threadTrain1, "窗口(1)");
            Thread t2 = new Thread(threadTrain1, "窗口(2)");
            Thread t3 = new Thread(threadTrain1, "窗口(3)");
            t1.start();
            t2.start();
            t3.start();
        }
    }
}
