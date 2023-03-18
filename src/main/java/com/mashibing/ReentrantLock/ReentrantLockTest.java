package com.mashibing.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xcy
 * @date 2021/8/12 - 11:43
 */
public class ReentrantLockTest {
	public static void main(String[] args) {
		ReentrantLock lock = new ReentrantLock();
		lock.lock();
		lock.unlock();
	}
}
