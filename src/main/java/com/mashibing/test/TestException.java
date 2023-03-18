package com.mashibing.test;

/**
 * @author xcy
 * @date 2021/8/9 - 15:36
 */

import java.util.concurrent.TimeUnit;

/**
 * 如果出现异常，默认情况下锁会被释放
 */
public class TestException {
	int count = 0;

	synchronized void method() {
		System.out.println(Thread.currentThread().getName() + "start");
		while (true) {
			count++;
			System.out.println(Thread.currentThread().getName() + ": count = " + count);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (count == 5) {
				int i = 1 / 0;
				System.out.println(i);
			}
		}
	}

	public static void main(String[] args) {
		TestException test = new TestException();
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				test.method();
			}
		};
		new Thread(runnable,"thread1").start();

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		new Thread(runnable,"thread2").start();
	}
}
