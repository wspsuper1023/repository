package com.paic.thread.security;

/**
 * @Author: WUSHIPING
 * @Date:   2020/12/13 23:04
 * @Description: ͬ��������Ԫ����
 */
public class ThreadTrain2 implements  Runnable {

    // �ܹ���һ���Ż�Ʊ ��һ������static���εĻ����������������class�ļ������ص�ʱ��ͻᱻ��ʼ����
    private static int trainCount = 100;
    private Object obj = new Object();
    public boolean flag = true;

    @Override
    public void run() {
        if (flag) {  //ִ��ͬ�������this��
            while (trainCount > 0) {
                synchronized (ThreadTrain2.class) {   //�����obj��������ͬһ������������̰߳�ȫ���⣬����101��Ʊ
                    if (trainCount > 0) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println(Thread.currentThread().getName()+",���۵�"+(100-trainCount+1)+"��Ʊ");

                        trainCount--;
                    }
                }
            }

        } else {    //ִ��ͬ������
            while (trainCount>0) {
                sale();
            }
        }

    }

    private static synchronized void sale() {  //ͬ������:ʹ��this������̬ͬ��������ʹ��this����ʹ�õ��ǵ�ǰ�ֽ����ļ�
        if (trainCount > 0) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+",���۵�"+(100-trainCount+1)+"��Ʊ");
            trainCount--;
        }
    }

    public static class ThreadDemo {
        public static void main(String[] args) throws InterruptedException {
            ThreadTrain2 threadTrain2 = new ThreadTrain2();
            Thread t1 = new Thread(threadTrain2, "���ڣ�1��");
            Thread t2 = new Thread(threadTrain2, "���ڣ�2��");
            t1.start();
            Thread.sleep(400);
            threadTrain2.flag = false;
            t2.start();
        }
    }
}
