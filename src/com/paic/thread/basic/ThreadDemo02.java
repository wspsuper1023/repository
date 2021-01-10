package com.paic.thread.basic;

class CreateThreadDemo02 implements Runnable {
	
	@Override
	public void run() {
		for (int i = 0; i < 30; i++) {
			System.out.println("?????run,i:" + i);
		}
	}
}

public class ThreadDemo02 {
	
	/**
	 * ???Runnable?????Thread???
	 * ??????Runnable??????????????????
	 * */
	public static void main(String[] args) {
		CreateThreadDemo02 t1 = new CreateThreadDemo02();
		Thread thread = new Thread(t1);
		thread.start();
		for (int i = 0; i < 30; i++) {
			System.out.println("?????i:" + i);
		}
	}
	
}
