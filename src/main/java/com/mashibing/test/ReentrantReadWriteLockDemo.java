package com.mashibing.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author xcy
 * @date 2021/8/23 - 9:18
 */

/**
 * 将ReentrantReadWriteLock拆分为ReadLock和WriteLock
 * 使用场景：读多写少
 * 读锁ReadLock可以并发，写锁WriteLock与其他锁互斥
 * 写写互斥、写读互斥、读读不互斥
 */
public class ReentrantReadWriteLockDemo {
	private static volatile int count = 0;
	public static void readMethod() {
		System.out.println(count);
	}

	public static void writeMethod() {
		count++;
	}
	public static void main(String[] args) {
		ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
		ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
		ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

		List<Thread> threads = new ArrayList<>();

		Thread readThread1 = new Thread(() -> {
			try {
				readLock.lock();
				readMethod();
			} finally {
				readLock.unlock();
			}
		});

		Thread readThread2 = new Thread(() -> {
			try {
				readLock.lock();
				readMethod();
			} finally {
				readLock.unlock();
			}
		});

		Thread writeThread = new Thread(() -> {
			try {
				writeLock.lock();
				writeMethod();
			} finally {
				writeLock.unlock();
			}
		});
		threads.add(readThread1);
		threads.add(readThread2);
		threads.add(writeThread);

		for (Thread thread : threads) {
			thread.start();
		}
	}
}
