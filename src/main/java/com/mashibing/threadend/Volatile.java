package com.mashibing.threadend;

/**
 * @author xcy
 * @date 2021/8/19 - 10:35
 */

import com.mashibing.threadbasic.SleepHelper;

/**
 * 通过设置volatile修饰的变量来控制线程的结束
 * volatile线程中没有其它的线程业务逻辑是可以结束线程的
 */
public class Volatile {
	private static volatile boolean running = true;

	public static void main(String[] args) {

		Thread thread = new Thread(() -> {
			long count = 0L;
			while (running) {
				//wait

				count++;
			}
			System.out.println("go on : " + count);
		});

		thread.start();

		SleepHelper.sleepSeconds(2);

		running = false;
		//1962821783
		//1933526410
	}
}
