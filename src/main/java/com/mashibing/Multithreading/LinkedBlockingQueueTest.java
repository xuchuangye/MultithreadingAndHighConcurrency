package com.mashibing.Multithreading;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author xcy
 * @date 2021/8/14 - 9:40
 */

/**
 * BlockingQueue阻塞队列
 * LinkedBlockingQueue队列是有界的，也就是最大个数为Integer.MAX_VALUE
 */
public class LinkedBlockingQueueTest {
	static BlockingQueue<String> sBQ = new LinkedBlockingQueue<>();

	static Random r = new Random();

	public static void main(String[] args) {
		new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				try {
					//put，如果添加元素失败，就会进入阻塞状态
					sBQ.put("a" + i);
					TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "thread1").start();

		for (int i = 0; i < 5; i++) {
			new Thread(() -> {
				for (; ; ) {
					try {
						//take，如果获取元素失败，就会进入阻塞状态
						System.out.println(Thread.currentThread().getName() + " take -" + sBQ.take());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}, "c" + i).start();
		}
	}
}
