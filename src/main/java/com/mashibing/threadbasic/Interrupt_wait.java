package com.mashibing.threadbasic;

/**
 * @author xcy
 * @date 2021/8/19 - 9:01
 */

/**
 * wait方法通过设置标志位抛出异常，根据捕获的异常做出相应的处理
 */
public class Interrupt_wait {
	public static void main(String[] args) {
		Thread thread = new Thread(() -> {
			for (; ; ) {
				synchronized (Interrupt_wait.class) {
					try {
						//wait抛出异常InterruptedException，只要设置标志位就会抛出
						Interrupt_wait.class.wait();
					}
					//catch捕获异常之后会将标志位自动进行复位，防止出现再次抛出异常而捕获不到的问题
					catch (InterruptedException e) {
						System.out.println("currentThread is interrupted");
						System.out.println(Thread.currentThread().isInterrupted());
						break;
					}
				}
			}
		});

		thread.start();
		SleepHelper.sleepSeconds(2);
		thread.interrupt();
	}
}
