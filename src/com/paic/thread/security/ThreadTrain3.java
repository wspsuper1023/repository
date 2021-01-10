package com.paic.thread.security;

/**
 * @Author: WUSHIPING
 * @Date:   2020/12/13 23:04
 * @Description: 死锁
 */
public class ThreadTrain3 implements  Runnable {

    private int trainCount = 100;
    public boolean flag = true;
    private Object mutex = new Object();

    @Override
    public void run() {
        if (flag) {
            while (true) {
                synchronized (mutex) {
                    // 锁(同步代码块)在什么时候释放？ 代码执行完， 自动释放锁.
                    // 如果flag为true 先拿到 obj锁,在拿到this 锁、 才能执行。
                    // 如果flag为false先拿到this,在拿到obj锁，才能执行。
                    // 死锁解决办法:不要在同步中嵌套同步。
                    sale();
                }
            }
        } else {
            while (true) {
                sale();
            }
        }
    }

    /**
     *
     * @methodDesc: 功能描述:(出售火车票)
     * @author: 余胜军
     * @param:
     * @createTime:2017年8月9日 下午9:49:11
     * @returnType: void
     * @copyright:上海每特教育科技有限公司
     */
    public synchronized void sale() {
        synchronized (mutex) {
            if (trainCount > 0) {
                try {
                    Thread.sleep(40);
                } catch (Exception e) {

                }
                System.out.println(Thread.currentThread().getName() + ",出售 第" + (100 - trainCount + 1) + "张票.");
                trainCount--;
            }
        }
    }

    public static class ThreadDemo {
        public static void main(String[] args) throws InterruptedException {
            ThreadTrain3 threadTrain3 = new ThreadTrain3();
            Thread t1 = new Thread(threadTrain3, "窗口（1）");
            Thread t2 = new Thread(threadTrain3, "窗口（2）");
            t1.start();
            Thread.sleep(40);
            threadTrain3.flag = false;
            t2.start();
        }
    }
}
