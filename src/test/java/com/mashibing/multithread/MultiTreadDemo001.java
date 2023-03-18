package com.mashibing.multithread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author xcy
 * @date 2023/2/27 - 8:13
 */
public class MultiTreadDemo001 {
	public static void main(String[] args) {
		Thread thread1 = new Thread() {
			@Override
			public void run() {
				System.out.println(currentThread().getName() + "正在执行");
			}
		};
		thread1.start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("继承Runnable");
			}
		}).start();

		FutureTask<String> stringFutureTask = new FutureTask<String>(new Callable<String>() {
			@Override
			public String call() throws Exception {
				System.out.println("实现Callable");
				return "返回值：实现Callable";
			}

		});
		new Thread(stringFutureTask).start();
		try {
			System.out.println(stringFutureTask.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
