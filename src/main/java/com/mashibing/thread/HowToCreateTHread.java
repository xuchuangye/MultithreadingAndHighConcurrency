package com.mashibing.thread;

/**
 * @author xcy
 * @date 2021/8/9 - 9:19
 */

/**
 * 线程的启动方式
 */
public class HowToCreateTHread {
	static class Thread1 extends Thread {
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " : extends Thread");
		}
	}
	static class Thread2 implements Runnable {

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " : implements Runnable");
		}
	}

	public static void main(String[] args) {
		new Thread1().start();

		new Thread(new Thread2()).start();

		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " : Lambda Thread");
		}).start();
	}
}
