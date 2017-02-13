package com.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
	private static Lock lock = new ReentrantLock();
	private static Lock lock1 = new ReentrantLock(true); // fair locking
	
	private static int i = 0;
	ReentrantLockExample objectLock = new ReentrantLockExample();

	public static void main(String[] args) {

		for (int i = 0; i < 5; i++) {
			System.out.println(getCount());
		}

		System.out.println("Reentrant " + getReentrantCount());
	}

	private synchronized static int getCount() {
		System.out.println(Thread.currentThread().getName() + " value of i: "
				+ i);
		i++;
		return i;
	}

	private static int getReentrantCount() {
		lock.lock();
		try {
			i++;
			System.out.println(Thread.currentThread().getName()
					+ " value of i: " + i);
			return i;
		} finally {
			lock.unlock();
		}
	}

}
