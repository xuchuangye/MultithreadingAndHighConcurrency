package com.mashibing.threadbasic;

/**
 * @author xcy
 * @date 2021/8/19 - 9:06
 */

/**
 * synchronized正在竞争锁的时候，不可能被interrupt干扰
 */
public class Interrupt_synchronized {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		Thread thread1 = new Thread(() -> {
			synchronized (Interrupt_synchronized.class) {
				//sleep方法调用的时候不会释放这把锁
				SleepHelper.sleepSeconds(10);
			}
		});

		thread1.start();
		SleepHelper.sleepSeconds(1);

		Thread thread2 = new Thread(() -> {
			synchronized (Interrupt_synchronized.class) {

			}
			System.out.println("thread2 end");
		});

		thread2.start();
		SleepHelper.sleepSeconds(1);
		thread2.interrupt();
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("time is :" + (end - start));
	}
}
