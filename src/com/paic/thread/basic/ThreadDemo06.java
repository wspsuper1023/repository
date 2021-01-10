package com.paic.thread.basic;

/**
 * ���̵߳�����״̬��
 * �½�  new Thread()
 * ׼�� start	��������ִ�У��ȴ�CPU����
 * ����  CPU����
 * ����	 sleep	wait	����״̬	���µȴ�CPU����
 * ֹͣ	stop ��ִ�����
 * 
 * 
 * join���������ã�
 * �߳�A B
 * A.join 
 * A�ȴ�Bִ����Ϻ���ִ�У�A�ͷ�CPUִ��Ȩ��
 */
public class ThreadDemo06 {

	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 60; i++) {
					System.out.println("���߳�i:" + i);
				}
			}
		});
		thread.start();
		
		//���߳������߳���ִ��
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 30; i++) {
			System.out.println("���߳�i:" + i);
		}
		
		System.out.println("���߳�ִ�н���......");
	}

}
