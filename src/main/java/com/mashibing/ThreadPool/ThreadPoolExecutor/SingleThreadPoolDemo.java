package com.mashibing.ThreadPool.ThreadPoolExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xcy
 * @date 2021/8/15 - 15:36
 */

/**
 * SingleThreadExecutor
 * 保证任务顺序执行
 */
public class SingleThreadPoolDemo {
	public static void main(String[] args) {
		ExecutorService service = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 5; i++) {
			final int j = i;
			service.execute(() -> {
				System.out.println(j + " " + Thread.currentThread().getName());
			});
		}
		System.out.println(Integer.MAX_VALUE);//2147483647
		/*new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				final int j = i;
				System.out.println(j + " " + Thread.currentThread().getName());
			}
		}).start();*/
		service.shutdown();
	}
}
