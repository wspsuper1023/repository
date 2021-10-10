package com.paic.thread.demo.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Admin
 * @Date: 2021/10/10 21:26
 * @Description:    Java������/�������п�������-05
 *          ģ����������������
 */
public class ProducerAndConsumer {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
        ProducerThread p1 = new ProducerThread(queue);
        ProducerThread p2 = new ProducerThread(queue);
        ConsumerThread c1 = new ConsumerThread(queue);

        Thread pThread1 = new Thread(p1);
        Thread pThread2 = new Thread(p2);
        Thread cThread1 = new Thread(c1);

        pThread1.start();
        pThread2.start();
        cThread1.start();

        Thread.sleep(10*1000);  //ִ��10��
        p1.stop();
        p2.stop();
    }
}

class ProducerThread implements Runnable {
    private BlockingQueue<String> queue;
    private static AtomicInteger count = new AtomicInteger();
    private volatile boolean flag = true;

    ProducerThread(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            System.out.println("�����߳�����...");
            while (flag) {
                System.out.println("������������....");
                String data = count.incrementAndGet()+"";

                // �����ݴ��������
                boolean offer = queue.offer(data, 2, TimeUnit.SECONDS);
                if (offer) {
                    System.out.println("������,����" + data + "��������,�ɹ�.");
                } else {
                    System.out.println("������,����" + data + "��������,ʧ��.");
                }
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("�������˳��߳�");
        }
    }

    public void stop() {
        this.flag = false;
    }
}

class ConsumerThread implements Runnable {
    private BlockingQueue<String> queue;
    private volatile boolean flag = true;

    ConsumerThread(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            System.out.println("�����߳�����...");
            while (flag) {
                System.out.println("������,���ڴӶ����л�ȡ����..");
                String data = queue.poll(2, TimeUnit.SECONDS);
                if (null != data ) {
                    System.out.println("������,�õ������е�����data:" + data);
                    Thread.sleep(1000);
                } else {
                    System.out.println("������,����2��δ��ȡ������..");
                    this.stop();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("�������˳��߳�");
        }
    }

    private void stop() {
        this.flag = false;
    }
}
