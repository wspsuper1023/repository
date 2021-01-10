package com.paic.thread.basic;

class CreateThread04 implements Runnable {
	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			System.out.println("���߳�id:" + getId() + "���߳�:" + i);
			System.out.println("��ǰ�߳�id:" + Thread.currentThread().getId() + ",��ǰ�߳�name:"
					+ Thread.currentThread().getName() + ",i:" + i);
			if (i == 5) {
				Thread.currentThread().stop(); // �̲߳���ȫ
			}
		}
	}
}

/**
 * ���õ��߳�API
 */
public class ThreadDemo04 {

	public static void main(String[] args) {
		System.out.println("���߳�name:" + Thread.currentThread().getName());
		for (int i = 0; i < 3; i++) {
			new Thread(new CreateThread04()).start();
		}
	}

}
