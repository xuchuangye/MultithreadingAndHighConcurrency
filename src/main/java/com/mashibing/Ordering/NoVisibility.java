package com.mashibing.Ordering;

/**
 * @author xcy
 * @date 2021/8/20 - 9:52
 */

/**
 * 可见性和有序性
 */
public class NoVisibility {
	//保证可见性
	private static boolean ready = false;
	private static int number;

	private static class ReaderThread extends Thread {
		@Override
		public void run() {
			while (!ready) {
				Thread.yield();
			}
			System.out.println(number);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ReaderThread thread = new ReaderThread();

		thread.start();

		//保证有序性
		number = 42;
		ready = true;

		thread.join();
	}
}
