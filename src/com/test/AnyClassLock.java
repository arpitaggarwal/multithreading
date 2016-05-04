package com.test;

public class AnyClassLock {

	private final static Object lock = new Object();

	public void toLockMethod() {
		synchronized (lock) {
			// other thread safe code
		}
	}

}
