package com.mashibing.Atomicity;

import com.mashibing.threadbasic.SleepHelper;

/**
 * @author xcy
 * @date 2021/8/20 - 11:33
 */
public class WhatIsLock {
	private static Object o = new Object();

	public static void main(String[] args) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				//monitor（管程）锁
				synchronized (o) {
					System.out.println(Thread.currentThread().getName() + " start");
					SleepHelper.sleepSeconds(2);
					System.out.println(Thread.currentThread().getName() + " end");
				}
			}
		};

		for (int i = 0; i < 3; i++) {
			new Thread(runnable, "thread - " + i).start();
		}
	}
}
