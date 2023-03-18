package com.mashibing.InterviewQuestions2;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author xcy
 * @date 2021/8/14 - 15:56
 */

/**
 * 使用两个线程打印输出A1B2C3...Z26
 * 使用Synchronized、wait、notify方法，不能百分之百控制thread1先执行或者thread2先执行
 * 但是使用CountDownLatch可以百分之百控制thread1先执行或者thread2先执行
 */
public class Question_SynchronizedWaitNotify {
	static Thread thread1 = null;
	static Thread thread2 = null;

	static List<Integer> integers = new LinkedList<>();
	static List<Character> characters = new LinkedList<>();

	static {
		for (int i = 1; i <= 26; i++) {
			integers.add(i);
		}
		for (char c = 'A'; c <= 'Z'; c++) {
			characters.add(c);
		}
	}
	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(1);
		//创建锁对象
		final Object lock = new Object();
		thread1 = new Thread(() -> {
			synchronized(lock) {
				for (Character c : characters) {
					//thread1线程先打印
					System.out.print(c);
					latch.countDown();
					try {
						//唤醒thread2线程
						lock.notify();
						//然后当前线程进入阻塞状态
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				//因为总有一个线程处于wait阻塞状态，所以必须要使用notify结束程序
				lock.notify();
			}
		}, "thread1");

		thread2 = new Thread(() -> {
			try {
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized(lock) {
				for (Integer integer : integers) {
					//thread2线程打印输出
					System.out.print(integer);
					try {
						//唤醒thread1线程
						lock.notify();

						//让线程thread2等待
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				//因为总有一个线程处于wait阻塞状态，所以必须要使用notify结束程序
				lock.notify();
			}
		}, "thread2");

		thread1.start();
		thread2.start();
	}
}
