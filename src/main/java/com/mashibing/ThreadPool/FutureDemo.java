package com.mashibing.ThreadPool;

/**
 * @author xcy
 * @date 2021/8/15 - 9:00
 */

import java.util.concurrent.*;

/**
 * Future接收并封装Callable接口call方法的返回值
 * FutureTask类实现RunnableFuture接口，而RunnableFuture接口同时继承Runnable接口和Future接口
 * FutureTask类可以灵活使用
 */
public class FutureDemo {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		//------------------创建线程的方式-------------------
		FutureTask<Integer> task = new FutureTask<Integer>(() -> {
			TimeUnit.MILLISECONDS.sleep(500);
			int sum = 0;
			for (int i = 1; i <= 100; i++) {
				sum += i;
			}
			return sum;
		});

		new Thread(task).start();
		//get()当获取不到值时会进入阻塞状态，直到得到值才会继续往下执行
		System.out.println(task.get());

		//------------------创建线程池的方式-------------------
		ExecutorService service = Executors.newFixedThreadPool(5);
		//submit方法具有异步的特性
		Future<Integer> future = service.submit(() -> {
			TimeUnit.MILLISECONDS.sleep(500);
			return 1;
		});
		System.out.println(future.get());
		System.out.println(future.isDone());
		service.shutdown();
	}
}
