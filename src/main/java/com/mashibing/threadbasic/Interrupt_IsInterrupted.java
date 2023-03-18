package com.mashibing.threadbasic;

/**
 * @author xcy
 * @date 2021/8/19 - 8:29
 */

/**
 * 设置标志位interrupt/查询标志位isInterrupted
 */
public class Interrupt_IsInterrupted {
	public static void main(String[] args) {
		Thread thread = new Thread(() -> {
			for (; ; ) {
				//isInterrupted查询标志位
				if (Thread.currentThread().isInterrupted()) {
					System.out.println("currentThread is interrupted");
					System.out.println(Thread.currentThread().isInterrupted());
					break;
				}
			}
		});

		thread.start();

		SleepHelper.sleepSeconds(2);
		//interrupt设置标志位
		thread.interrupt();
	}
}
