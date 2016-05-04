package com.test;

public class ClassLevelLock implements Runnable {

	@Override
	public void run() {
		objectLock();
	}

	public void objectLock() {
		System.out.println(Thread.currentThread().getName());
		synchronized (ClassLevelLock.class) {
			System.out.println("synchronized block "
					+ Thread.currentThread().getName());
			System.out.println("synchronized block "
					+ Thread.currentThread().getName() + " end");
		}
	}

	public static void main(String[] args) {
		ClassLevelLock b1 = new ClassLevelLock();
		Thread t1 = new Thread(b1);
		Thread t2 = new Thread(b1);

		ClassLevelLock b2 = new ClassLevelLock();
		Thread t3 = new Thread(b2);

		t1.setName("t1");
		t2.setName("t2");
		t3.setName("t3");

		t1.start();
		t2.start();
		t3.start();
	}
}
