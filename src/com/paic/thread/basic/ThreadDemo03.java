package com.paic.thread.basic;

abstract class Parent {
	public abstract void add();
}

/**
 * ʹ�������ڲ��ഴ���߳�
 * */
public class ThreadDemo03 {
	
	public static void main(String[] args) {
//		Parent parent = new Parent() {
//			
//			@Override
//			public void add() {
//				System.out.println("�����ڲ���");
//			}
//		};
//		parent.add();
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 20; i++) {
					System.out.println("���߳�:" + i);
				}
			}
		});
		thread.start();
		
		for (int i = 0; i < 20; i++) {
			System.out.println("���߳�:" + i);
		}
	}
	
}
