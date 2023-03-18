package com.mashibing.Visibility;

/**
 * @author xcy
 * @date 2021/8/19 - 16:52
 */

import java.util.concurrent.CountDownLatch;

/**
 * 缓存行
 */
public class CacheLine {
	public static /*final*/ long count = 10_0000_0000L;

	private static class Clazz {
		private long p1,p2,p3,p4,p5,p6,p7;
		public long num = 0L;
		private long p9,p10,p11,p12,p13,p14,p15;
	}

	public static /*final*/ Clazz[] arr = new Clazz[2];

	static {
		arr[0] = new Clazz();
		arr[1] = new Clazz();
	}

	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(2);

		Thread thread1 = new Thread(() -> {
			for (long i = 0; i < count; i++) {
				arr[0].num = i;
			}
			latch.countDown();
		});

		Thread thread2 = new Thread(() -> {
			for (long i = 0; i < count; i++) {
				arr[1].num = i;
			}
			latch.countDown();
		});

		final long start = System.nanoTime();

		thread1.start();
		thread2.start();

		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println((System.nanoTime() - start) / 100_0000);//749
	}
}
