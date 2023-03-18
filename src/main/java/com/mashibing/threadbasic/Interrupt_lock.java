package com.mashibing.threadbasic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xcy
 * @date 2021/8/19 - 9:16
 */

/**
 * lock竞争锁的时候，是不可能被interrupt干扰的
 */
public class Interrupt_lock {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		//产生锁
		ReentrantLock lock = new ReentrantLock();

		Thread thread1 = new Thread(() -> {
			lock.lock();
			try {
				TimeUnit.MILLISECONDS.sleep(10000);
				System.out.println("thread1 end");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		});

		thread1.start();

		SleepHelper.sleepSeconds(1);

		Thread thread2 = new Thread(() -> {
			lock.lock();
			try {
				System.out.println("thread2 end");
			} finally {
				lock.unlock();
			}
		});

		thread2.start();

		SleepHelper.sleepSeconds(1);
		//设置标志位
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
