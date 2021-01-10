package com.paic.thread.basic;

/**
 * @Author: Admin
 * @Date: 2020/12/14 23:43
 * @Description: �ػ��߳�
 */
public class MyThread implements Runnable {
    @Override
    public void run() {
        while (DaemonThreadDemo.run_flag) { //ֹͣ���б�־
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "����" + (i+1));
            }
        }
    }


}

class DaemonThreadDemo {
    public static boolean run_flag = true;  //�߳�t1���б�־

    public static void main(String[] args) {
        Thread t1 = new Thread(new MyThread(), "�û��߳�");

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            run_flag = false;
        }, "�ػ��߳�");
        t2.setDaemon(true);

        t1.start();
        t2.start();
    }
}
