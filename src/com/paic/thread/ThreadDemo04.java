package com.paic.thread;

class CreateThread04 implements Runnable {
	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			System.out.println("子线程id:" + getId() + "子线程:" + i);
			System.out.println("当前线程id:" + Thread.currentThread().getId() + ",当前线程name:"
					+ Thread.currentThread().getName() + ",i:" + i);
			if (i == 5) {
				Thread.currentThread().stop(); // 线程不安全
			}
		}
	}
}

/**
 * 常用的线程API
 */
public class ThreadDemo04 {

	public static void main(String[] args) {
		System.out.println("主线程name:" + Thread.currentThread().getName());
		for (int i = 0; i < 3; i++) {
			new Thread(new CreateThread04()).start();
		}
	}

}
