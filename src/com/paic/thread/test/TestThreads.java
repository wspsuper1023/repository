package com.paic.thread.test;

/**
 * @Author: Admin
 * @Date: 2020/12/15 0:52
 * @Description: 设计4个线程，其中两个线程每次对j增加1，另外两个线程对j每次减少1
 */
public class TestThreads {

    private int j = 1;

    private class Inc implements Runnable {
        @Override
        public void run() {
            inc();
        }
    }

    private class Dec implements Runnable {

        @Override
        public void run() {
            dec();
        }
    }

    private void inc() {
        j++;
        System.out.println(Thread.currentThread().getName() + ",inc:" + j);
    }

    private void dec() {
        j--;
        System.out.println(Thread.currentThread().getName() + ",dec:" + j);
    }

    public static void main(String[] args) {
        TestThreads testThreads = new TestThreads();

        Thread thread = null;

        Inc inc = testThreads.new Inc();
        Dec dec = testThreads.new Dec();

        for (int i = 0; i < 2; i++) {
            thread = new Thread(inc);
            thread.start();

            thread = new Thread(dec);
            thread.start();
        }
    }

}

