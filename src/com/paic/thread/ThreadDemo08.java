package com.paic.thread;

class ThreadTrain2 implements Runnable {

	// 总共100张火车票
	public int count = 100;
	private Object obj = new Object();
	public boolean flag = true;

	@Override
	public void run() {

		if (flag) { // 执行同步代码块this锁
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			synchronized (obj) {
				if (count > 0) {
					System.out.println(Thread.currentThread().getName() + "，出售第" + (100 - count + 1) + "张票.");
					count--;
				}
			}
		} else { // 执行同步函数
			while (count > 0) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				sale();
			}
		}

	}

	public synchronized void sale() {
		if (count > 0) {
			System.out.println(Thread.currentThread().getName() + "，出售第" + (100 - count + 1) + "张票.");
			count--;
		}
	}
}

//同步函数
public class ThreadDemo08 {

	public static void main(String[] args) throws InterruptedException {
		ThreadTrain2 threadTrain = new ThreadTrain2();

		Thread thread1 = new Thread(threadTrain, "窗口1");
		Thread thread2 = new Thread(threadTrain, "窗口2");

		thread1.start();
		Thread.sleep(50);
		threadTrain.flag = false;
		thread2.start();
	}

}
