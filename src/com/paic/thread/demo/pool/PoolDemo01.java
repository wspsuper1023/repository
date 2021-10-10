package com.paic.thread.demo.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Admin
 * @Date: 2021/10/10 22:40
 * @Description:    线程池及锁的深度化课堂例子-01
 *      为什么要使用线程池？
 *      1、降低资源消耗：重复利用已创建的线程降低线程创建和销毁所消耗的资源；
 *      2、提高相应速度：当任务到达时，不需要等线程创建就能立即执行；
 *      3、提高线程的客观理性：线程池不会无限制创建线程，所以可以节约系统资源，提高系统稳定性；线程池还会统一分配、调优、监控。
 *
 *      线程池的4种创建方式？
 *      newCachedThreadPool、newFixedThreadPool 、newScheduledThreadPool 、newSingleThreadExecutor
 */
public class PoolDemo01 {

    public static void main(String[] args) {
        createNewCachedThreadPool();
        createNewFixedThreadPool();
        createNewScheduledThreadPool();
        createNewSingleThreadExecutor();
    }

    //1、newCachedThreadPool：创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
    private static void createNewCachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int temp = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+",i:"+temp);
                }
            });
        }
        executorService.shutdown();
    }

    //2、createNewFixedThreadPool：创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
    private static void createNewFixedThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            final int temp = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+",i:"+temp);
                }
            });
        }
        executorService.shutdown();
    }

    //3、newScheduledThreadPool：创建一个定长线程池，支持定时及周期性任务执行
    private static void createNewScheduledThreadPool() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5); //最多创建5个线程
        for (int i = 0; i < 10; i++) {
            final int temp = i;
            executorService.schedule(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+",i:"+temp);
                }
            }, 3, TimeUnit.SECONDS);    //3秒后执行
        }

        executorService.shutdown();
    }

    //4、newSingleThreadExecutor：创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
    private static void createNewSingleThreadExecutor() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int temp = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+",i:"+temp);    //结果依次输出，相当于顺序执行各个任务
                }
            });
        }
        executorService.shutdown();
    }
}
