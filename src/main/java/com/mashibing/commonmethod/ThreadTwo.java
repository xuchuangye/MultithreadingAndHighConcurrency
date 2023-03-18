package com.mashibing.commonmethod;

/**
 * @author xcy
 * @date 2021/8/9 - 10:00
 */
public class ThreadTwo {
	private int count = 100;

	public synchronized void print() {
		count--;
		System.out.println(Thread.currentThread().getName());
	}
}
