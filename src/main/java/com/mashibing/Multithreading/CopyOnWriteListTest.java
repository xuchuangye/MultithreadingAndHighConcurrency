package com.mashibing.Multithreading;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author xcy
 * @date 2021/8/14 - 9:11
 */

/**
 * 适用场景：读比较多，写比较少
 */
public class CopyOnWriteListTest {
	public static void main(String[] args) {
		List<String> lists = new CopyOnWriteArrayList<>();
		Random random = new Random();
		Thread[] threads = new Thread[100];

		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(() -> {
				for (int j = 0; j < 1000; j++) {
					lists.add("a" + random.nextInt(10000));
				}
			});
		}

		runAndComputeTime(threads);
		System.out.println(lists);
	}

	public static void runAndComputeTime(Thread[] threads) {
		long start = System.currentTimeMillis();
		for (Thread thread : threads) {
			thread.start();
		}

		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("花费时间：" + (end - start));
	}
}
