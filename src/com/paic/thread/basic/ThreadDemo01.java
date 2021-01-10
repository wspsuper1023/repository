package com.paic.thread.basic;

class CreateThreadDemo01 extends Thread {
	
	@Override
	public void run() {
		for (int i = 0; i < 30; i++) {
			System.out.println("run,i:" + i);
		}
	}
}

public class ThreadDemo01 {
	
	/**
	 * �����߳�����Щ��ʽ��
	 * 1���̳�thread��
	 * 2��ʵ��runnable�ӿ�
	 * 3��ʹ�������ڲ���
	 * 4��callable
	 * 5��ʹ���̳߳ش����߳�
	 * */
	public static void main(String[] args) {
		CreateThreadDemo01 t1 = new CreateThreadDemo01();
		/**
		 * 1�������̣߳����ǵ���run���������ǵ���start����
		 * 2��ʹ��startִ�ж��̣߳����첽���ã����벻������������У�run�����������������
		 * */
		t1.start();
//		t1.run();
		for (int i = 0; i < 30; i++) {
			System.out.println("main,i:" + i);
		}
	}
	
}
