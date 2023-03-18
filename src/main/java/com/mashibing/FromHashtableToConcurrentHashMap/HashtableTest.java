package com.mashibing.FromHashtableToConcurrentHashMap;

import java.util.Hashtable;
import java.util.UUID;

/**
 * @author xcy
 * @date 2021/8/13 - 17:30
 */
public class HashtableTest {
	static Hashtable<UUID, UUID> m = new Hashtable<>();

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

	static class MyThread extends Thread {
		int start;
		//gap表示每一个线程装多少个数
		int gap = count / THREAD_COUNT;

		public MyThread(int start) {
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
		//------------------------------ write ----------------------------
		long start = System.currentTimeMillis();
		Thread[] threads = new Thread[THREAD_COUNT];

		for (int i = 0; i < threads.length; i++) {
			threads[i] = new MyThread(i * (count / THREAD_COUNT));
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
		System.out.println("Hashtable write code time is : " + (end - start));//440

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
		System.out.println("Hashtable read code time is : " + (tail - head));//42226
	}
}
