package com.paic.thread;

/**
 * 多线程的运行状态：
 * 新建  new Thread()
 * 准备 start	不会立马执行，等待CPU调度
 * 运行  CPU运行
 * 休眠	 sleep	wait	就绪状态	重新等待CPU调度
 * 停止	stop 或执行完毕
 * 
 * 
 * join方法的作用：
 * 线程A B
 * A.join 
 * A等待B执行完毕后再执行（A释放CPU执行权）
 */
public class ThreadDemo06 {

	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 60; i++) {
					System.out.println("子线程i:" + i);
				}
			}
		});
		thread.start();
		
		//主线程让子线程先执行
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 30; i++) {
			System.out.println("主线程i:" + i);
		}
		
		System.out.println("主线程执行结束......");
	}

}
