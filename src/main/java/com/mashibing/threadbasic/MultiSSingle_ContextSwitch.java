package com.mashibing.threadbasic;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author xcy
 * @date 2021/8/18 - 15:22
 */

/**
 * 测试单线程、包含两个线程的多线程、包含大数量线程的多线程的效率
 */
public class MultiSSingle_ContextSwitch {
	private static double[] nums = new double[1_0000_0000];
	private static Random random = new Random();
	private static DecimalFormat df = new DecimalFormat("0.00");
	
	static {
		for (int i = 0; i < nums.length; i++) {
			nums[i] = random.nextDouble();
		}
	}

	/**
	 * 单线程
	 */
	private static void method1() {
		long start = System.currentTimeMillis();

		double result = 0;

		for (int i = 0; i < nums.length; i++) {
			result += nums[i];
		}

		long end = System.currentTimeMillis();
		System.out.println("method1花费的时间：" + (end - start) + ", result = " + df.format(result));
	}

	static double result1 = 0.0,result2 = 0.0,result3 = 0.0;

	/**
	 * 包含两个线程的多线程肯定比单线程效率高
	 */
	private static void method2() {
		Thread thread1 = new Thread(() -> {
			for (int i = 0; i < nums.length / 2; i++) {
				result1 += nums[i];
			}
		});

		Thread thread2 = new Thread(() -> {
			for (int i = nums.length / 2; i < nums.length; i++) {
				result2 += nums[i];
			}
		});
		long start = System.currentTimeMillis();
		thread1.start();
		thread2.start();

		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		result3 = result1 + result2;
		long end = System.currentTimeMillis();
		System.out.println("method2花费的时间：" + (end - start) + ", result = " + df.format(result3));
	}

	/**
	 * 线程数越大反而效率越低
	 */
	private static void method3() {
		final int threadCount = 32;

		Thread[] threads = new Thread[threadCount];
		double[] results = new double[threadCount];
		//段数
		final int segmentCount = nums.length / threadCount;
		CountDownLatch latch = new CountDownLatch(threadCount);

		for (int i = 0; i < threadCount; i++) {
			int m = i;
			threads[i] = new Thread(() -> {
				for (int j = m * segmentCount; j < (m + 1) * segmentCount && j < nums.length; j++) {
					results[m] += nums[j];
				}
			});

			latch.countDown();
		}
		double resultM3 = 0.0;
		long start = System.currentTimeMillis();
		for (Thread thread : threads) {
			thread.start();
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < results.length; i++) {
			resultM3 += results[i];
		}

		long end = System.currentTimeMillis();

		System.out.println("method3花费的时间：" + (end - start) + ", result = " + df.format(result3));


	}
	public static void main(String[] args) {
		method1();
		method2();
		method3();
	}
}
