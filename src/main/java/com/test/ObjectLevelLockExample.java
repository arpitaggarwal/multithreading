package com.test;

public class ObjectLevelLockExample {

	private int a = 0;
	private int b = 0;
	private Object lockA = new Object();
	private Object lockB = new Object();

	public void method1() {
		// do something ...
		synchronized (this) {
			a++;
		}
	}

	public void method2() {
		// do something ...
		synchronized (this) {
			b++;
		}
	}

	/** Refactored Code **/

	public void method3() {
		// do something ...
		synchronized (lockA) {
			a++;
		}
	}

	public void method4() {
		// do something ...
		synchronized (lockB) {
			b++;
		}
	}
}
