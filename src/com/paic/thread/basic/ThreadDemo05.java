package com.paic.thread.basic;

/**
 * �û��߳������̴߳������߳�
 * �û��̺߳����̻߳���Ӱ�죬�����������߳����ٶ����٣�
 * �ػ��̣߳�GC���������̵߳����ٶ�����
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
					System.out.println("���߳�i:" + i);
				}
			}
		});
		
		//�Ѹ��߳�����Ϊ�ػ��̣߳������߳�һ������
		thread.setDaemon(true);
		thread.start();
		
		for (int i = 0; i < 5; i++) {
			System.out.println("���̣߳�" + i);
		}
		
		System.out.println("���߳�ִ�н���......");
	}

}
