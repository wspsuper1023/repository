package com.paic.thread;

/**
 * T1 T2 T3三个线程依次执行
 * */
public class Test06 {

	public static void main(String[] args) throws InterruptedException {
		 Thread t1 =  new Thread() {
	            @Override
	            public void run() {
	                for (int i = 1; i < 10; i++) {
	                    System.out.println("t1:" + i);
	 
	                }
	            }
	        };
	         
	        Thread t2 =  new Thread() {
	            @Override
	            public void run() {
	                for(int i = 10; i < 20;i++) {
	                    System.out.println("t2:" + i);
	                }
	            }
	        };
	 
	        Thread t3 =  new Thread() {
	            @Override
	            public void run() {
	                for(int i = 20; i < 30; i++) {
	                    System.out.println("t3:" + i);
	                }
	            }
	        };
	        
	        t1.start();
	        t1.join();
	        t2.start();
	        t2.join();
	        t3.start();
	        t3.join();
	}
}
