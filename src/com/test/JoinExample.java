package com.test;

import java.util.Date;

public class JoinExample implements Runnable {
	
	public static void main(String[] args) {
		JoinExample obj = new JoinExample();

		Thread t1 = new Thread(obj, "t1");
		Thread t2 = new Thread(obj, "t2");
		Thread t3 = new Thread(obj, "t3");
		t1.start();
		try {
			t3.join();
			t3.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t2.start();
		// t3.start();
	}

	@Override
	public void run() {
		Thread thread = Thread.currentThread();
		System.out.println("RunnableJob is being run by " + thread.getName()
				+ " state : " + thread.getState() + " at " + new Date());
	}
}
