package com.mashibing.juc;

/**
 * @author xcy
 * @date 2021/8/10 - 17:34
 */

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 倒计时计时器
 */
public class CountDownLatchTest {
	public static void usingCountDownLatch() {
		Thread[] threads = new Thread[100];
		//此时count为100
		CountDownLatch countDownLatch = new CountDownLatch(threads.length);

		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(() -> {
				int sum = 0;
				for (int j = 0; j < 10000; j++) {
					sum+= j;
				}
				//将count值减1
				countDownLatch.countDown();
			});
		}

		for (Thread thread : threads) {
			thread.start();
		}

		for (int i = 0; i < threads.length; i++) {
			try {
				//调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
				countDownLatch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("end latch...");
	}
	
	public static void usingJoin() {
		Thread[] threads = new Thread[100];

		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(() -> {
				int sum = 0;
				for (int j = 0; j < 10000; j++) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					sum+= j;
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

		System.out.println("end join...");
	}

	public static void main(String[] args) {
		System.out.println("主线程执行...");
		System.out.println("等待两个线程执行完毕…… ……");
		usingJoin();
		usingCountDownLatch();
		System.out.println("两个子线程都执行完毕，继续执行主线程");
	}
}
