package com.paic.thread;

abstract class Parent {
	public abstract void add();
}

/**
 * 使用匿名内部类创建线程
 * */
public class ThreadDemo03 {
	
	public static void main(String[] args) {
//		Parent parent = new Parent() {
//			
//			@Override
//			public void add() {
//				System.out.println("匿名内部类");
//			}
//		};
//		parent.add();
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 20; i++) {
					System.out.println("子线程:" + i);
				}
			}
		});
		thread.start();
		
		for (int i = 0; i < 20; i++) {
			System.out.println("主线程:" + i);
		}
	}
	
}
