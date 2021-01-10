package com.paic.thread.security;

/**
 * @Author: Admin
 * @Date: 2020/12/14 22:32
 * @Description: volatile刷新全局变量至子线程，但不保证原子性【线程不安全】
 */
public class ThreadVolatile {

    public static void main(String[] args) throws InterruptedException {
        ThreadVolatileDemo threadVolatileDemo = new ThreadVolatileDemo();
        threadVolatileDemo.start();
        Thread.sleep(3000);

        //主线程修改全局变量为false
        threadVolatileDemo.setFlag(false);

        System.out.println("flag值已经修改为：" + threadVolatileDemo.flag);
        Thread.sleep(1000);
        System.out.println("flag值已经修改为：" + threadVolatileDemo.flag);
    }



}

class ThreadVolatileDemo extends Thread {

    public volatile boolean flag = true;

    @Override
    public void run() {
        System.out.println("子线程开始执行...");
        while (flag) {

        }
        System.out.println("子线程执行结束...");
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

}
