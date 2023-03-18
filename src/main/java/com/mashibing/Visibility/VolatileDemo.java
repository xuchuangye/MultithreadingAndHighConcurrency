package com.mashibing.Visibility;

import com.mashibing.threadbasic.SleepHelper;

/**
 * @author xcy
 * @date 2021/8/19 - 15:33
 */

/**
 * volatile修饰基本属性
 * volatile保证属性的可见性
 */
public class VolatileDemo {
	private static /*volatile*/ boolean running = true;
	public static void method() {
		System.out.println("method start");

		while (running) {
			System.out.println("hello");
		}

		System.out.println("method end");
	}

	public static void main(String[] args) {
		new Thread(VolatileDemo::method,"thread").start();

		SleepHelper.sleepSeconds(1);

		running = false;
	}
}
