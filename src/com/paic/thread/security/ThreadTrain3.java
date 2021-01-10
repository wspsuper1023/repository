package com.paic.thread.security;

/**
 * @Author: WUSHIPING
 * @Date:   2020/12/13 23:04
 * @Description: ����
 */
public class ThreadTrain3 implements  Runnable {

    private int trainCount = 100;
    public boolean flag = true;
    private Object mutex = new Object();

    @Override
    public void run() {
        if (flag) {
            while (true) {
                synchronized (mutex) {
                    // ��(ͬ�������)��ʲôʱ���ͷţ� ����ִ���꣬ �Զ��ͷ���.
                    // ���flagΪtrue ���õ� obj��,���õ�this ���� ����ִ�С�
                    // ���flagΪfalse���õ�this,���õ�obj��������ִ�С�
                    // ��������취:��Ҫ��ͬ����Ƕ��ͬ����
                    sale();
                }
            }
        } else {
            while (true) {
                sale();
            }
        }
    }

    /**
     *
     * @methodDesc: ��������:(���ۻ�Ʊ)
     * @author: ��ʤ��
     * @param:
     * @createTime:2017��8��9�� ����9:49:11
     * @returnType: void
     * @copyright:�Ϻ�ÿ�ؽ����Ƽ����޹�˾
     */
    public synchronized void sale() {
        synchronized (mutex) {
            if (trainCount > 0) {
                try {
                    Thread.sleep(40);
                } catch (Exception e) {

                }
                System.out.println(Thread.currentThread().getName() + ",���� ��" + (100 - trainCount + 1) + "��Ʊ.");
                trainCount--;
            }
        }
    }

    public static class ThreadDemo {
        public static void main(String[] args) throws InterruptedException {
            ThreadTrain3 threadTrain3 = new ThreadTrain3();
            Thread t1 = new Thread(threadTrain3, "���ڣ�1��");
            Thread t2 = new Thread(threadTrain3, "���ڣ�2��");
            t1.start();
            Thread.sleep(40);
            threadTrain3.flag = false;
            t2.start();
        }
    }
}
