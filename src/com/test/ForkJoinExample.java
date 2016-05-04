package com.test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinExample extends RecursiveTask<Integer> {

	private static final long serialVersionUID = 1L;

	int[] numbers;
	int start;
	int end;
	int THRESHOLD = 5;

	public ForkJoinExample(int[] numbers) {
		this(numbers, 0, numbers.length);
	}

	public ForkJoinExample(int[] numbers, int start, int end) {
		this.numbers = numbers;
		this.start = start;
		this.end = end;
	}

	public static void main(String[] args) {
		int SIZE = 500;
		final int[] numbers = new int[SIZE];
		int biggest = 0;

		for (int i = 0; i < SIZE; i++) {
			numbers[i] = (int) (Math.random() * 10000);
			if (numbers[i] > biggest) {
				biggest = numbers[i];
			}
		}
		System.out.println("Biggest number generated: " + biggest);

		ForkJoinPool pool = new ForkJoinPool(4);
		ForkJoinExample obj = new ForkJoinExample(numbers);
		System.out.println("Biggest number found: " + pool.invoke(obj));
	}

	@Override
	protected Integer compute() {
		int length = end - start;
		int max = 0;
		if (length < THRESHOLD) {
			for (int x = start; x < end; x++) {
				if (numbers[x] > max) {
					max = numbers[x];
				}
			}
			return max;
		} else {
			int split = length / 2;
			ForkJoinExample left = new ForkJoinExample(numbers, start, start
					+ split);
			left.fork();
			ForkJoinExample right = new ForkJoinExample(numbers, start + split,
					end);
			return Math.max(right.compute(), left.join());
		}

	}
}
