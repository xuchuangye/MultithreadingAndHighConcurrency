package com.mashibing.Multithreading;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * @author xcy
 * @date 2021/8/14 - 8:53
 */
public class ConcurrentMapTest {
	public static void main(String[] args) {
		Map<String,String> map = new ConcurrentHashMap<String, String>();
		//高并发并且排序
		//Map<String,String> map = new ConcurrentSkipListMap<String, String>;

		Random r = new Random();
		Thread[] threads = new Thread[100];
		CountDownLatch latch = new CountDownLatch(threads.length);
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(() -> {
				for (int j = 0; j < 10000; j++) {
					map.put("a" + r.nextInt(100000),"a" + r.nextInt(100000));
					latch.countDown();
				}
			});
		}
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
	}
}
