package com.paic.thread.basic;

/**
 * @Author: Admin
 * @Date: 2021/1/13 0:05
 * @Description: 线程停止
 */
public class InterruptThread {
    public static void main(String[] args) {
        ThreadStopDemo threadStopDemo = new ThreadStopDemo();
        Thread t1 = new Thread(threadStopDemo);
        Thread t2 = new Thread(threadStopDemo);
        Thread t3 = new Thread(threadStopDemo);

        t1.start();
        t2.start();
        t3.start();

        int count = 0;
        while (true) {
            System.out.println("main run..."+count);

            if (count == 30) {
                t1.interrupt();
                t2.interrupt();
                t3.interrupt();
                break;
            }
            count++;
        }
    }
}

class ThreadStopDemo implements Runnable {
    boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                stopThread();
            }
            System.out.println("thread run ...");
        }
    }

    private void stopThread() {
        flag = false;
    }
}
