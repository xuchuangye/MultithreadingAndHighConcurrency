package com.mashibing.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xcy
 * @date 2021/8/10 - 16:18
 */

/**
 * 可重入锁ReentrantLock测试tryLock
 */
public class ReentrantLockTest3 {

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

	//tryLock是否得到这把锁，默认没有得到
	boolean locked = false;

	public synchronized void method2() {
		try {
			//如果得到则打印输出
			locked = lock.tryLock(5, TimeUnit.SECONDS);
			System.out.println("method2 ..." + locked);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//如果得到这把锁就需要进行锁的关闭
			if (locked)
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		ReentrantLockTest3 reentrantLockTest = new ReentrantLockTest3();
		new Thread(reentrantLockTest::method1).start();

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		new Thread(reentrantLockTest::method2).start();
	}
}
