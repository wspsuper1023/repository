package com.paic.thread.basic;

/**
 * @Author: Admin
 * @Date: 2021/1/12 22:30
 * @Description: 本地线程   ThreadLocal为每个线程提供一个本地变量
 */
public class ThreadLocalDemo01 {
    public static void main(String[] args) {
        ResNumber resNumber = new ResNumber();
        LocalThreadDemo t1 = new LocalThreadDemo(resNumber);
        LocalThreadDemo t2 = new LocalThreadDemo(resNumber);
        LocalThreadDemo t3 = new LocalThreadDemo(resNumber);

        t1.start();
        t2.start();
        t3.start();
    }
}

class ResNumber {
    public static Integer count;

    public static ThreadLocal<Integer> threadLocal = new ThreadLocal<>() {
        protected Integer initialValue() {
            return 0;
        }
    };

    public Integer getNumber() {
        int count = threadLocal.get() + 1;
        threadLocal.set(count);
        return count;
    }
}

class LocalThreadDemo extends Thread {
    private ResNumber resNumber;

    public LocalThreadDemo(ResNumber resNumber) {
        this.resNumber = resNumber;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+",number:"+resNumber.getNumber());
        }
    }
}
