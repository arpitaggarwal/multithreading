package com.test;

public class StopThreadExample extends Thread {
	
	boolean bExit = false;

	public void exit(boolean bExit) {
		this.bExit = bExit;
	}

	@Override
	public void run() {
		while (!bExit) {
			System.out.println("Thread is running");
			try {
				Thread.sleep(500);
			} catch (InterruptedException ex) {
			}
		}
	}

}
