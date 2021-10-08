package com.paic.thread.demo.communication;

/**
 * @Author: Admin
 * @Date: 2021/10/7 22:45
 * @Description: 线程间通讯课堂例子04：本地线程
 *
 * ThreadLocal为每个线程提供一个本地变量
 */
public class ThreadLocalDemo {

    public static void main(String[] args) {
        ResNumber resNumber = new ResNumber();

        ThreadLocalTest t1 = new ThreadLocalTest(resNumber);
        ThreadLocalTest t2 = new ThreadLocalTest(resNumber);
        ThreadLocalTest t3 = new ThreadLocalTest(resNumber);

        t1.start();
        t2.start();
        t3.start();
    }
}

class ThreadLocalTest extends Thread {
    private ResNumber resNumber;

    ThreadLocalTest(ResNumber resNumber) {
        this.resNumber = resNumber;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(getName()+",number:"+resNumber.getNumber());
        }
    }
}

class ResNumber {
    Integer count;

    ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
        protected Integer initialValue() {
            return 0;
        };
    };

    public Integer getNumber() {
        count = threadLocal.get()+1;
        threadLocal.set(count);
        return count;
    }
}
