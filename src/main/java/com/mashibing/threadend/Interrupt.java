package com.mashibing.threadend;

import com.mashibing.threadbasic.SleepHelper;

/**
 * @author xcy
 * @date 2021/8/19 - 10:47
 */
public class Interrupt {
	public static void main(String[] args) {
		Thread thread1 = new Thread(() -> {
			while (Thread.currentThread().isInterrupted()) {

			}
			System.out.println(Thread.currentThread().isInterrupted());
		});

		Thread thread2 = new Thread(() -> {
			while (!Thread.interrupted()) {

			}
			System.out.println(Thread.currentThread().isInterrupted());
		});
		thread1.start();
		thread2.start();
		SleepHelper.sleepSeconds(1);
		thread1.interrupt();
		thread2.interrupt();
	}
}
