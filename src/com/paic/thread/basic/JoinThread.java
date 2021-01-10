package com.paic.thread.basic;

/**
 * @Author: Admin
 * @Date: 2020/12/15 0:07
 * @Description: join方法
 */
public class JoinThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+"----i:"+i);
        }
    }
}

class JoinThreadDemo {
    public static void main(String[] args) {
        JoinThread joinThread = new JoinThread();
        Thread t1 = new Thread(joinThread);
        Thread t2 = new Thread(joinThread);

        t1.start();
        t2.start();

        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 100; i++) {
            System.out.println("main----i:"+i);
        }
    }
}
