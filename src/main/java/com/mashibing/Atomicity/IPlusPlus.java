package com.mashibing.Atomicity;

import java.util.concurrent.CountDownLatch;

/**
 * @author xcy
 * @date 2021/8/20 - 11:04
 */
public class IPlusPlus {
	private static long n = 0L;

	public static void main(String[] args) throws InterruptedException {
		Thread[] threads = new Thread[100];

		CountDownLatch latch = new CountDownLatch(threads.length);
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(() -> {
				for (int j = 0; j < 10000; j++) {
					synchronized (IPlusPlus.class) {
						n++;
					}
				}
				latch.countDown();
			});
		}

		for (Thread thread : threads) {
			thread.start();
		}

		latch.await();
		System.out.println(n);
	}
}
