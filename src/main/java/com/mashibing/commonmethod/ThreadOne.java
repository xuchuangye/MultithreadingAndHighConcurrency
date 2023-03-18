package com.mashibing.commonmethod;

/**
 * @author xcy
 * @date 2021/8/9 - 9:59
 */
public class ThreadOne {
	private int count = 100;

	public void print() {
		synchronized (this) {
			count--;
			System.out.println(Thread.currentThread().getName());
		}
	}
}
