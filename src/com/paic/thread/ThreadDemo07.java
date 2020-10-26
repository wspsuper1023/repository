package com.paic.thread;

class ThreadTrain1 implements Runnable {
	
	//总共100张火车票
	int count = 100;
//	private Object obj = new Object();
	
	@Override
	public void run() {
		while (count > 0) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sale();
		}
	}
	
	public void sale() {
		synchronized (this) {
			if (count > 0) {
				System.out.println(Thread.currentThread().getName()+"，出售第"+(100-count+1)+"张票.");
				count --;
			}
		}
	}
}

//抢火车票问题
public class ThreadDemo07 {

	public static void main(String[] args) {
		ThreadTrain1 threadTrain = new ThreadTrain1();
		
		Thread thread1 = new Thread(threadTrain, "窗口1");
		Thread thread2 = new Thread(threadTrain, "窗口2");
		Thread thread3 = new Thread(threadTrain, "窗口3");
		
		thread1.start();
		thread2.start();
		thread3.start();
	}

}
