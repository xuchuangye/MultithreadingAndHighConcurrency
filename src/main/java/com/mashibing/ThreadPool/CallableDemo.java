package com.mashibing.ThreadPool;

import java.util.concurrent.*;

/**
 * @author xcy
 * @date 2021/8/15 - 8:37
 */

/**
 * Callable接口中的call方法具有返回值
 */
public class CallableDemo /*implements Callable<Integer>*/ {
	/*@Override
	public Integer call() throws Exception {
		int sum = 0;
		for (int i = 1; i <= 100; i++) {
			sum += i;
		}
		return sum;
	}*/

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		//------------方式一------------
		/*CallableDemo callableDemo = new CallableDemo();
		ExecutorService executorService = Executors.newCachedThreadPool();
		Future<Integer> submit = executorService.submit(callableDemo);
		try {
			System.out.println(submit.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		executorService.shutdown();*/

		//------------方式二------------
		ExecutorService service = Executors.newFixedThreadPool(5);
		Future<Integer> future = service.submit(() -> {
			int sum = 0;
			for (int i = 1; i <= 100; i++) {
				sum += i;
			}
			return sum;
		});
		System.out.println(future.get());
		System.out.println(future.isDone());
		service.shutdown();
	}
}
