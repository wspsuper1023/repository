package com.paic.thread;

class CreateThreadDemo02 implements Runnable {
	
	@Override
	public void run() {
		for (int i = 0; i < 30; i++) {
			System.out.println("子线程run,i:" + i);
		}
	}
}

public class ThreadDemo02 {
	
	/**
	 * 使用Runnable好还是Thread好？
	 * 一般实现Runnable接口多，面向接口，可以多继承
	 * */
	public static void main(String[] args) {
		CreateThreadDemo02 t1 = new CreateThreadDemo02();
		Thread thread = new Thread(t1);
		thread.start();
		for (int i = 0; i < 30; i++) {
			System.out.println("主线程i:" + i);
		}
	}
	
}
