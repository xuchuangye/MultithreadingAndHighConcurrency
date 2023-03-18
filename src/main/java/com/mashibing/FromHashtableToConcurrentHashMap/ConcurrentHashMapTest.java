package com.mashibing.FromHashtableToConcurrentHashMap;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xcy
 * @date 2021/8/13 - 17:54
 */
public class ConcurrentHashMapTest {
	static ConcurrentHashMap<UUID,UUID> m = new ConcurrentHashMap<UUID,UUID>();

	static int count = Constants.COUNT;
	static UUID[] keys = new UUID[count];
	static UUID[] values = new UUID[count];
	static final int THREAD_COUNT = Constants.THREAD_COUNT;

	static {
		for (int i = 0; i < count; i++) {
			keys[i] = UUID.randomUUID();
			values[i] = UUID.randomUUID();
		}
	}

	static class MyThread_ extends Thread {
		int start;
		//gap表示每一个线程装多少个数
		int gap = count / THREAD_COUNT;

		public MyThread_(int start) {
			this.start = start;
		}

		@Override
		public void run() {
			for (int i = start; i < start + gap; i++) {
				m.put(keys[i], values[i]);
			}
		}
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		Thread[] threads = new Thread[THREAD_COUNT];

		for (int j = 0; j < threads.length; j++) {
			threads[j] = new MyThread_(j * (count / THREAD_COUNT));
		}

		for (Thread t : threads) {
			t.start();
		}

		for (Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("ConcurrentHashMap write code time is :" + (end - start));//1297

		System.out.println(m.size());

		//------------------------------ read ----------------------------
		long head = System.currentTimeMillis();

		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(() -> {
				for (int j = 0; j < 10000000; j++) {
					m.get(keys[10]);
				}
			});
		}

		for (Thread t : threads) {
			t.start();
		}

		for (Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		long tail = System.currentTimeMillis();
		System.out.println("ConcurrentHashMap read code time is ：" + (tail - head));//1413
	}
}
