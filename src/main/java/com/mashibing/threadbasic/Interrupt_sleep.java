package com.mashibing.threadbasic;

import java.util.concurrent.TimeUnit;

/**
 * @author xcy
 * @date 2021/8/19 - 8:46
 */

/**
 * sleep通过设置标志位抛出异常，可以根据抛出的异常进行处理
 */
public class Interrupt_sleep {
	public static void main(String[] args) {
		Thread thread = new Thread(() -> {
			for (;;) {
				try {
					//sleep抛出异常InterruptedException，只要设置标志位就会抛出
					TimeUnit.MILLISECONDS.sleep(10000);
				}
				//catch捕获异常之后会将标志位自动进行复位，防止出现再次抛出异常而捕获不到的问题
				catch (InterruptedException e) {
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
	}
}
