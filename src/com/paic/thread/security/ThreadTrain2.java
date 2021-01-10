package com.paic.thread.security;

/**
 * @Author: WUSHIPING
 * @Date:   2020/12/13 23:04
 * @Description: 同步函数单元测试
 */
public class ThreadTrain2 implements  Runnable {

    // 总共有一百张火车票 当一变量被static修饰的话存放在永久区，当class文件被加载的时候就会被初始化。
    private static int trainCount = 100;
    private Object obj = new Object();
    public boolean flag = true;

    @Override
    public void run() {
        if (flag) {  //执行同步代码块this锁
            while (trainCount > 0) {
                synchronized (ThreadTrain2.class) {   //如果用obj锁，不是同一把锁，会产生线程安全问题，卖出101张票
                    if (trainCount > 0) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println(Thread.currentThread().getName()+",出售第"+(100-trainCount+1)+"张票");

                        trainCount--;
                    }
                }
            }

        } else {    //执行同步函数
            while (trainCount>0) {
                sale();
            }
        }

    }

    private static synchronized void sale() {  //同步函数:使用this锁；静态同步函数不使用this锁，使用的是当前字节码文件
        if (trainCount > 0) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+",出售第"+(100-trainCount+1)+"张票");
            trainCount--;
        }
    }

    public static class ThreadDemo {
        public static void main(String[] args) throws InterruptedException {
            ThreadTrain2 threadTrain2 = new ThreadTrain2();
            Thread t1 = new Thread(threadTrain2, "窗口（1）");
            Thread t2 = new Thread(threadTrain2, "窗口（2）");
            t1.start();
            Thread.sleep(400);
            threadTrain2.flag = false;
            t2.start();
        }
    }
}
