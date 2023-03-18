package com.mashibing.staticmethod;

/**
 * @author xcy
 * @date 2021/8/9 - 10:06
 */
public class ThreadOne {
	private static int count = 100;

	public static void print() {
		synchronized (ThreadOne.class) {
			count--;
			System.out.println(Thread.currentThread().getName());
		}
	}
}
