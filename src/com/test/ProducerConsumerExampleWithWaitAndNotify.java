package com.test;

import java.util.ArrayList;
import java.util.List;

/**
 * http://howtodoinjava.com/2015/01/08/how-to-work-with-wait-notify-and-
 * notifyall-in-java/
 *
 */
public class ProducerConsumerExampleWithWaitAndNotify {
	public static void main(String[] args) {
		List<Integer> taskQueue = new ArrayList<Integer>();
		int MAX_CAPACITY = 5;
		Thread tProducer = new Thread(new ProducerThread(taskQueue,
				MAX_CAPACITY), "Producer");
		Thread tConsumer = new Thread(new ConsumerThread(taskQueue), "Consumer");
		tProducer.start();
		tConsumer.start();
	}
}

class ProducerThread implements Runnable {
	List<Integer> taskQueue;
	int MAX_CAPACITY;

	public ProducerThread(List<Integer> taskQueue, int MAX_CAPACITY) {
		this.taskQueue = taskQueue;
		this.MAX_CAPACITY = MAX_CAPACITY;
	}

	@Override
	public void run() {
		int counter = 0;
		while (true) {
			produce(counter++);
		}
	}

	private void produce(int counter) {
		synchronized (taskQueue) {
			while (taskQueue.size() > MAX_CAPACITY) {
				try {
					taskQueue.wait();
				} catch (InterruptedException e) {
				}
			}
			System.out.println("Produced :: " + counter);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			taskQueue.add(counter);
			taskQueue.notifyAll();
		}
	}
}

class ConsumerThread implements Runnable {
	List<Integer> taskQueue;

	public ConsumerThread(List<Integer> taskQueue) {
		this.taskQueue = taskQueue;
	}

	@Override
	public void run() {
		while (true) {
			consume();
		}
	}

	private void consume() {
		synchronized (taskQueue) {
			while (taskQueue.isEmpty()) {
				try {
					taskQueue.wait();
				} catch (InterruptedException e) {
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			int element = taskQueue.remove(0);
			System.out.println("Consumed :: " + element);
			taskQueue.notifyAll();
		}
	}
}