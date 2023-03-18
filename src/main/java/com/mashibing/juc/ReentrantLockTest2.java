package com.mashibing.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xcy
 * @date 2021/8/10 - 16:18
 */

/**
 * 可重入锁测试
 */
public class ReentrantLockTest2 {

	ReentrantLock lock = new ReentrantLock();


	public synchronized void method1() {

		try {
			lock.lock();
			for (int i = 0; i < 10; i++) {
				TimeUnit.SECONDS.sleep(1);
				System.out.println(i);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public synchronized void method2() {
		try {
			lock.lock();
			System.out.println("method2 ...");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		ReentrantLockTest2 reentrantLockTest = new ReentrantLockTest2();
		new Thread(reentrantLockTest::method1).start();

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		new Thread(reentrantLockTest::method2).start();
	}
}
