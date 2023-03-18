package com.mashibing.juc;

import java.util.concurrent.TimeUnit;

/**
 * @author xcy
 * @date 2021/8/10 - 16:18
 */

/**
 * synchronized本身就是可重入锁的一种
 */
public class ReentrantLockTest1 {
	public synchronized void method1() {
		for (int i = 0; i < 10; i++) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(i);
		}
	}

	public synchronized void method2() {
		System.out.println("method2 ...");
	}

	public static void main(String[] args) {
		ReentrantLockTest1 reentrantLockTest = new ReentrantLockTest1();
		new Thread(reentrantLockTest::method1).start();

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		new Thread(reentrantLockTest::method2).start();
	}
}
