package com.mashibing.test;

/**
 * @author xcy
 * @date 2021/8/10 - 15:44
 */

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * 性能测试
 * AtomicLong :100000000,time : 2340
 * countSync :100000000,time : 4092
 * LongAdder :100000000,time : 487
 * 总结：
 * 为什么Atomic要比Sync效率高，因为Sync操作需要加锁，而Atomic实现无锁操作
 * 为什么LongAdder要比Atomic效率还要高，因为LongAdder内部使用了分段锁
 */
public class PerformanceTest {

	public static void main(String[] args) {
		AtomicLong countAtomic = new AtomicLong(0L);
		final long[] countSync = {0L};
		LongAdder countAdder = new LongAdder();

		Thread[] threads = new Thread[1000];

		//测试AtomicLong
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(() -> {
				for (int j = 0; j < 100000; j++) {
					countAtomic.incrementAndGet();
				}
			});
		}
		long startAtomic = System.currentTimeMillis();

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

		long endAtomic = System.currentTimeMillis();
		System.out.println("AtomicLong :" + countAtomic + ",time : " + (endAtomic - startAtomic));

		Object lock = new Object();
		//测试count
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 100000; j++) {
						synchronized (lock) {
							countSync[0]++;
						}
					}
				}
			});
		}

		long startCount = System.currentTimeMillis();

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

		long endCount = System.currentTimeMillis();
		System.out.println("countSync :" + countSync[0] + ",time : " + (endCount - startCount));


		//测试LongAdder
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(() -> {
				for (int j = 0; j < 100000; j++) {
					countAdder.increment();
				}
			});
		}

		long startAdder = System.currentTimeMillis();

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

		long endAdder = System.currentTimeMillis();
		System.out.println("LongAdder :" + countAdder + ",time : " + (endAdder - startAdder));
	}
}
