package com.mashibing.threadbasic;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xcy
 * @date 2021/8/19 - 9:26
 */

/**
 * ReentrantLock中的lockInterruptibly方法表示可以被interrupt干扰
 */
public class Interrupt_lockInterruptibly {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		ReentrantLock lock = new ReentrantLock();

		Thread thread1 = new Thread(() -> {
			System.out.println("thread1 start");
			lock.lock();
			try {
				SleepHelper.sleepSeconds(10);
			} finally {
				lock.unlock();
			}
			System.out.println("thread1 end");
		});

		thread1.start();

		SleepHelper.sleepSeconds(1);

		Thread thread2 = new Thread(() -> {
			System.out.println("thread2 start");
			try {
				//lockInterruptibly表示可以被打断
				lock.lockInterruptibly();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				lock.unlock();
			}
			System.out.println("thread2 end");
		});

		thread2.start();
		SleepHelper.sleepSeconds(1);

		thread2.interrupt();

		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		System.out.println("time is :" + (end - start));
	}
}
