package com.paic.thread.demo.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Admin
 * @Date: 2021/10/10 21:26
 * @Description:    Java并发包/并发队列课堂例子-05
 *          模拟生产者与消费者
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

        Thread.sleep(10*1000);  //执行10秒
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
            System.out.println("生产线程启动...");
            while (flag) {
                System.out.println("正在生产数据....");
                String data = count.incrementAndGet()+"";

                // 将数据存入队列中
                boolean offer = queue.offer(data, 2, TimeUnit.SECONDS);
                if (offer) {
                    System.out.println("生产者,存入" + data + "到队列中,成功.");
                } else {
                    System.out.println("生产者,存入" + data + "到队列中,失败.");
                }
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("生产者退出线程");
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
            System.out.println("消费线程启动...");
            while (flag) {
                System.out.println("消费者,正在从队列中获取数据..");
                String data = queue.poll(2, TimeUnit.SECONDS);
                if (null != data ) {
                    System.out.println("消费者,拿到队列中的数据data:" + data);
                    Thread.sleep(1000);
                } else {
                    System.out.println("消费者,超过2秒未获取到数据..");
                    this.stop();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("消费者退出线程");
        }
    }

    private void stop() {
        this.flag = false;
    }
}
