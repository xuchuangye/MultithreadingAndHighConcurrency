package com.mashibing.Atomicity;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xcy
 * @date 2021/8/20 - 14:25
 */

/**
 * AtomicInteger原子性的Integer
 */
public class AtomicIntegerDemo {
	private static AtomicInteger count = new AtomicInteger(0);

	/*public void add() {
		for (int i = 0; i < 10000; i++) {
			//自带原子性操作
			count.incrementAndGet();
		}
	}*/

	public static void main(String[] args) throws InterruptedException {
		AtomicIntegerDemo atomicIntegerDemo = new AtomicIntegerDemo();
		/*List<Thread> threads = new ArrayList<Thread>();

		for (int i = 0; i < 100; i++) {
			threads.add(new Thread(atomicIntegerDemo::add,"thread -" + i));
		}

		for (Thread thread : threads) {
			thread.start();
		}

		for (Thread thread : threads) {
			thread.join();
		}*/
		Thread[] threads = new Thread[100];
		CountDownLatch latch = new CountDownLatch(threads.length);
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(() -> {
				for (int j = 0; j < 10000; j++) {
					count.incrementAndGet();//count++
				}
				latch.countDown();
			});
		}

		for (Thread thread :threads) {
			thread.start();
		}

		for (Thread thread : threads) {
			thread.join();
		}
		latch.await();

		System.out.println(AtomicIntegerDemo.count);
	}
}
