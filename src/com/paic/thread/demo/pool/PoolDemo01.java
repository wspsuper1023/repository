package com.paic.thread.demo.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Admin
 * @Date: 2021/10/10 22:40
 * @Description:    �̳߳ؼ�������Ȼ���������-01
 *      ΪʲôҪʹ���̳߳أ�
 *      1��������Դ���ģ��ظ������Ѵ������߳̽����̴߳��������������ĵ���Դ��
 *      2�������Ӧ�ٶȣ������񵽴�ʱ������Ҫ���̴߳�����������ִ�У�
 *      3������̵߳Ŀ͹����ԣ��̳߳ز��������ƴ����̣߳����Կ��Խ�Լϵͳ��Դ�����ϵͳ�ȶ��ԣ��̳߳ػ���ͳһ���䡢���š���ء�
 *
 *      �̳߳ص�4�ִ�����ʽ��
 *      newCachedThreadPool��newFixedThreadPool ��newScheduledThreadPool ��newSingleThreadExecutor
 */
public class PoolDemo01 {

    public static void main(String[] args) {
        createNewCachedThreadPool();
        createNewFixedThreadPool();
        createNewScheduledThreadPool();
        createNewSingleThreadExecutor();
    }

    //1��newCachedThreadPool������һ���ɻ����̳߳أ�����̳߳س��ȳ���������Ҫ���������տ����̣߳����޿ɻ��գ����½��߳�
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

    //2��createNewFixedThreadPool������һ�������̳߳أ��ɿ����߳���󲢷������������̻߳��ڶ����еȴ�
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

    //3��newScheduledThreadPool������һ�������̳߳أ�֧�ֶ�ʱ������������ִ��
    private static void createNewScheduledThreadPool() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5); //��ഴ��5���߳�
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
            }, 3, TimeUnit.SECONDS);    //3���ִ��
        }

        executorService.shutdown();
    }

    //4��newSingleThreadExecutor������һ�����̻߳����̳߳أ���ֻ����Ψһ�Ĺ����߳���ִ�����񣬱�֤����������ָ��˳��(FIFO, LIFO, ���ȼ�)ִ��
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
                    System.out.println(Thread.currentThread().getName()+",i:"+temp);    //�������������൱��˳��ִ�и�������
                }
            });
        }
        executorService.shutdown();
    }
}
