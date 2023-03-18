package com.mashibing.juc;

/**
 * @author xcy
 * @date 2021/8/10 - 16:43
 */

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * interrupt打断
 * lockInterruptibly响应
 */
public class ReentrantLockTest4 {
	public static void main(String[] args) {
		ReentrantLock lock = new ReentrantLock();
		Thread thread1 = new Thread(() -> {
			try {
				lock.lock();
				System.out.println("thread1 start...");
				TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
				System.out.println("thread1 end...");
			} catch (Exception e) {
				System.out.println("interrupt");
			} finally {
				lock.unlock();
			}
		});

		thread1.start();

		Thread thread2 = new Thread(() -> {
			try {
				//lock.lock();
				lock.lockInterruptibly();//可以对interrupt做出响应
				System.out.println("thread2 start...");
				TimeUnit.SECONDS.sleep(5);
				System.out.println("thread2 end...");
			} catch (Exception e) {
				System.out.println("interrupt");
			} finally {
				lock.unlock();
			}
		});

		thread2.start();

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//打断线程Thread2的等待
		thread2.interrupt();
	}
}
