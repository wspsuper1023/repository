package com.paic.thread;

class ThreadTrain2 implements Runnable {

	// �ܹ�100�Ż�Ʊ
	public int count = 100;
	private Object obj = new Object();
	public boolean flag = true;

	@Override
	public void run() {

		if (flag) { // ִ��ͬ�������this��
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			synchronized (obj) {
				if (count > 0) {
					System.out.println(Thread.currentThread().getName() + "�����۵�" + (100 - count + 1) + "��Ʊ.");
					count--;
				}
			}
		} else { // ִ��ͬ������
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
			System.out.println(Thread.currentThread().getName() + "�����۵�" + (100 - count + 1) + "��Ʊ.");
			count--;
		}
	}
}

//ͬ������
public class ThreadDemo08 {

	public static void main(String[] args) throws InterruptedException {
		ThreadTrain2 threadTrain = new ThreadTrain2();

		Thread thread1 = new Thread(threadTrain, "����1");
		Thread thread2 = new Thread(threadTrain, "����2");

		thread1.start();
		Thread.sleep(50);
		threadTrain.flag = false;
		thread2.start();
	}

}
