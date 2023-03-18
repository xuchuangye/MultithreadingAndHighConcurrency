package com.mashibing.threadbasic;

/**
 * @author xcy
 * @date 2021/8/19 - 8:37
 */

/**
 * 设置标志位interrupt/查询当前线程标志位并重置标志位interrupted
 */
public class Interrupt_Interrupted {
	public static void main(String[] args) {
		Thread thread = new Thread(() -> {
			for (;;) {
				//查询标志位并重置标志位
				if (Thread.interrupted()) {
					System.out.println("currentThread is interrupted");
					System.out.println(Thread.currentThread().isInterrupted());
					break;
				}
			}
		});

		thread.start();

		SleepHelper.sleepSeconds(2);
		//设置标志位
		thread.interrupt();
		System.out.println("main: " + Thread.interrupted());
	}
}
