package com.test;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorFrameworkExample {

	public static void main(String args[]) {
		ExecutorService executor = Executors.newFixedThreadPool(3);

		Random random = new Random();

		int waitTime = 600;
		for (int i = 0; i < 10; i++) {
			String name = "NamePrinter " + i;
			int time = random.nextInt(500);
			waitTime += time;
			Runnable runner = new TaskPrint(name, time);
			System.out.println("Adding: " + name + " / " + time);
			Future future = executor.submit(runner);
			// future.get(timeout, unit)
		}
		try {
			Thread.sleep(waitTime);
			executor.shutdown();
			executor.awaitTermination(waitTime, TimeUnit.MILLISECONDS);
		} catch (InterruptedException ignored) {
		}
		System.exit(0);
	}
}

class TaskPrint implements Runnable {
	private final String name;
	private final int delay;

	public TaskPrint(String name, int delay) {
		this.name = name;
		this.delay = delay;
	}

	public void run() {
		System.out.println("Starting: " + name);
		try {
			Thread.sleep(delay);
		} catch (InterruptedException ignored) {
		}
		System.out.println("Done with: " + name);
	}
}
