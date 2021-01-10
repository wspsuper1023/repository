package com.paic.thread.basic;

/**
 * @Author: Admin
 * @Date: 2020/12/14 23:43
 * @Description: 守护线程
 */
public class MyThread implements Runnable {
    @Override
    public void run() {
        while (DaemonThreadDemo.run_flag) { //停止运行标志
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "报数" + (i+1));
            }
        }
    }


}

class DaemonThreadDemo {
    public static boolean run_flag = true;  //线程t1运行标志

    public static void main(String[] args) {
        Thread t1 = new Thread(new MyThread(), "用户线程");

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            run_flag = false;
        }, "守护线程");
        t2.setDaemon(true);

        t1.start();
        t2.start();
    }
}
