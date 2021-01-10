package com.paic.thread.basic;

/**
 * @Author: Admin
 * @Date: 2020/12/15 0:16
 * @Description: priority优先级   范围为1-10，其中10最高，默认值为5
 */
public class PriorityThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "---i:" + i);
        }
    }
}

class PriorityThreadDemo {
    public static void main(String[] args) {
        PriorityThread priorityThread = new PriorityThread();

        Thread t1 = new Thread(priorityThread);
        Thread t2 = new Thread(priorityThread);

        t2.setPriority(10);

        t1.start();
        t2.start();
    }
}
