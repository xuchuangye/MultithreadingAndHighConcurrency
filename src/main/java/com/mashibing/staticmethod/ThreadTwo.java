package com.mashibing.staticmethod;

/**
 * @author xcy
 * @date 2021/8/9 - 10:08
 */
public class ThreadTwo {
	private static int count = 100;
	public static synchronized void print() {
		count--;
		System.out.println(Thread.currentThread().getName());
	}
}
