package com.mashibing.test;

/**
 * @author xcy
 * @date 2021/8/9 - 10:35
 */
public class Thread_ implements Runnable{
	private int count = 100;

	/**
	 * synchronized既保证了原子性，又保证了可见性，以及有序性
	 * 有序性：如果不加synchronized，则打印结果非常的乱，加上synchronized，则打印结果是有序的
	 */
	@Override
	public synchronized void run() {
		count--;
		System.out.println(Thread.currentThread().getName() + " - count :" + count);
	}

	public static void main(String[] args) {
		/*Thread_ thread = new Thread_();
		for (int i = 0; i < 100; i++) {
			new Thread(thread, "THREAD" + i).start();
		}*/

		System.out.println(Integer.MAX_VALUE);
		//-2147483648
		//2147483647
	}
}
