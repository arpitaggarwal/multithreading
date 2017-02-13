package com.test;

public class SynchronizedBlockExample {
	/**
	 * Do not synchronize on non final field on synchronized block in Java.
	 * because reference of non final field may change any time and then
	 * different thread might synchronizing on different objects i.e. no
	 * synchronization at all. Best is to use String class, which is already
	 * immutable and declared final.
	 */
	private final Integer object = 10;

	public void add(int value) {
		// Synchronized Block
		synchronized (object) {
		}
	}
}
