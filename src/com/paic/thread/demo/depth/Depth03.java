package com.paic.thread.demo.depth;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: Admin
 * @Date: 2021/10/31 10:38
 * @Description: ��д�����ֽл�����������ʱ����д��д��ʱ���ܶ���
 */
public class Depth03 extends Thread {
    static Map<String, Object> cache = new HashMap<>();
    static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    static Lock readLock = lock.readLock();
    static Lock writeLock = lock.writeLock();

    public static Object read(String key) {
        readLock.lock();
        Object value;
        try {
            System.out.println(Thread.currentThread().getName()+"���ڶ�:key="+key);
            value = cache.get(key);
            System.out.println(Thread.currentThread().getName()+"���ѽ���:value="+value);
            System.out.println("--------------------------------------------");
        } catch (Exception e) {
            value = null;
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
        return value;
    }

    private static void write(String key, Object value) {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"����д:key="+key+",value="+value);
            cache.put(key, value);
            System.out.println(Thread.currentThread().getName()+"д�ѽ���......");
            System.out.println("--------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    Depth03.write(String.valueOf(i), i);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    Depth03.read(String.valueOf(i));
                }
            }
        }).start();
    }
}
