package com.mashibing.Ordering;

import java.util.concurrent.CountDownLatch;

/**
 * @author xcy
 * @date 2021/8/20 - 9:10
 */

/**
 * 根据程序的验证以及分析，两个语句有一定的可能性会交换顺序执行
 * 乱序有可能发生
 * 乱序的原因：
 * 为了提高效率
 * 乱序的原则：
 * 不影响单线程的最终一致性
 */
public class Disorder {
	private static int x = 0, y = 0;
	private static int a = 0, b = 0;

	public static void main(String[] args) throws InterruptedException {
		for (long i = 0; i < Long.MAX_VALUE; i++) {
			x = 0;
			y = 0;
			a = 0;
			b = 0;
			CountDownLatch latch = new CountDownLatch(2);

			Thread thread1 = new Thread(() -> {
				a = 1;
				x = b;

				latch.countDown();
			});

			Thread thread2 = new Thread(() -> {
				b = 1;
				y = a;

				latch.countDown();
			});

			thread1.start();
			thread2.start();

			latch.await();

			String result = "第" + i + "次(" + x + "," + y + ")";

			if (x == 0 && y == 0) {
				System.out.println(result);
				break;
			}
		}
	}
}
