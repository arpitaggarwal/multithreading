package com.test;

/**
 * http://www.programcreek.com/2009/02/notify-and-wait-example/
 *
 */
public class A {
	public static void main(String[] args) {
		B b = new B();
		b.start();

		synchronized (b) {
			try {
				System.out.println("Waiting for b to complete...");
				b.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("Total is: " + b.total);
		}
	}
}
