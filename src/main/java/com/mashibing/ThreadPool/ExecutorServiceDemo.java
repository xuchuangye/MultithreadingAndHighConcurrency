package com.mashibing.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xcy
 * @date 2021/8/15 - 8:29
 */

/**
 * ExecutorService继承Executor接口以及接口中的execute方法
 * 完善整个任务的生命周期
 */
public class ExecutorServiceDemo {
	public static void main(String[] args) {
		ExecutorService e = Executors.newCachedThreadPool();
		//execute表示拿到任务之后，马上执行
		e.execute(() -> {
			System.out.println("execute");
		});
		//submit表示拿到任务之后，将任务扔给线程池，执行由线程池说了算
		e.submit(() -> {

		});
	}
}
