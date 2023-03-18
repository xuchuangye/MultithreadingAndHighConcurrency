package com.mashibing.juc;

/**
 * @author xcy
 * @date 2021/8/11 - 10:11
 */

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * Read共享锁
 * Write排他锁
 */
public class ReadWriteLockTest {
	static ReentrantLock lock = new ReentrantLock();
	private static int value;

	//ReentrantReadWriteLock类是ReadWriteLock接口的实现类
	static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	//共享锁
	static Lock readLock = readWriteLock.readLock();
	//排他锁
	static Lock writeLock = readWriteLock.writeLock();

	public static void read(Lock lock) {
		try {
			lock.lock();
			Thread.sleep(1000);
			System.out.println("read over!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public static void write(Lock lock, int v) {
		try {
			lock.lock();
			Thread.sleep(1000);
			value = v;
			System.out.println("write over!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		Runnable read = () -> {
			//read(lock);
			read(readLock);
		};


		Runnable write = () -> {
			//write(lock, new Random().nextInt());
			write(writeLock, new Random().nextInt());
		};

		for (int i = 0; i < 18; i++) {
			new Thread(read).start();
		}
		for (int i = 0; i < 2; i++) {
			new Thread(write).start();
		}
	}
}
