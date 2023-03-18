package com.mashibing.Visibility;

import java.util.concurrent.CountDownLatch;
import sun.misc.Contended;
/**
 * @author xcy
 * @date 2021/8/20 - 8:30
 */

/**
 *
 * 参数-XX:-RestrictContended
 */
public class ContendedDemo {
	public static /*final*/ long count = 10_0000_0000L;

	private static class Clazz {
		//只有1.8起作用
		@Contended
		public long num = 0L;
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
