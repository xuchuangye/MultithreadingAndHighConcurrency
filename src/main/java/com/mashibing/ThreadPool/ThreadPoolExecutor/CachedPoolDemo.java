package com.mashibing.ThreadPool.ThreadPoolExecutor;

/**
 * @author xcy
 * @date 2021/8/15 - 15:49
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CachedThreadPool
 * 弹性线程池
 */
public class CachedPoolDemo {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		System.out.println(service);

		for (int i = 0; i < 2; i++) {
			service.execute(() -> {
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			});
		}

		System.out.println(service);

		try {
			TimeUnit.MILLISECONDS.sleep(80);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(service);
	}
}
