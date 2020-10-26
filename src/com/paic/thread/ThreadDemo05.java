package com.paic.thread;

/**
 * 用户线程是主线程创建的线程
 * 用户线程和主线程互不影响，不会随着主线程销毁而销毁；
 * 守护线程（GC）随着主线程的销毁而销毁
 */
public class ThreadDemo05 {

	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 30; i++) {
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("子线程i:" + i);
				}
			}
		});
		
		//把该线程设置为守护线程：跟主线程一起销毁
		thread.setDaemon(true);
		thread.start();
		
		for (int i = 0; i < 5; i++) {
			System.out.println("主线程：" + i);
		}
		
		System.out.println("主线程执行结束......");
	}

}
