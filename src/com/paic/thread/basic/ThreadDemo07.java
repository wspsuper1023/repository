package com.paic.thread.basic;

class ThreadTrain1 implements Runnable {
	
	//�ܹ�100�Ż�Ʊ
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
				System.out.println(Thread.currentThread().getName()+"�����۵�"+(100-count+1)+"��Ʊ.");
				count --;
			}
		}
	}
}

//����Ʊ����
public class ThreadDemo07 {

	public static void main(String[] args) {
		ThreadTrain1 threadTrain = new ThreadTrain1();
		
		Thread thread1 = new Thread(threadTrain, "����1");
		Thread thread2 = new Thread(threadTrain, "����2");
		Thread thread3 = new Thread(threadTrain, "����3");
		
		thread1.start();
		thread2.start();
		thread3.start();
	}

}
