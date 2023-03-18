package com.mashibing.juc;

/**
 * @author xcy
 * @date 2021/8/10 - 16:53
 */

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁
 */
public class ReentrantLockTest5 extends Thread{
	//公平锁，会先检查等待队列里有没有其他线程，如果有，则会等待其他线程先执行
	private static final ReentrantLock lock = new ReentrantLock(true);

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {

			try {
				lock.lock();
				System.out.println(Thread.currentThread().getName() + "： 获得锁");
			}/* catch (Exception e) {
				e.printStackTrace();
			} */finally {
				lock.unlock();
			}
		}
	}

	public static void main(String[] args) {
		ReentrantLockTest5 test5 = new ReentrantLockTest5();
		Thread thread1 = new Thread(test5);
		Thread thread2 = new Thread(test5);
		thread1.start();
		thread2.start();
	}
}
