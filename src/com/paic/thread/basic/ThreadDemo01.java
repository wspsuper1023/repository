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
	 * 创建线程有哪些方式？
	 * 1、继承thread类
	 * 2、实现runnable接口
	 * 3、使用匿名内部类
	 * 4、callable
	 * 5、使用线程池创建线程
	 * */
	public static void main(String[] args) {
		CreateThreadDemo01 t1 = new CreateThreadDemo01();
		/**
		 * 1、启动线程：不是调用run方法，而是调用start方法
		 * 2、使用start执行多线程，是异步调用，代码不会从上自下运行，run方法会从上自下运行
		 * */
		t1.start();
//		t1.run();
		for (int i = 0; i < 30; i++) {
			System.out.println("main,i:" + i);
		}
	}
	
}
