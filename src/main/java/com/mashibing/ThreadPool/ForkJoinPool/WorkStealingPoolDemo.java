package com.mashibing.ThreadPool.ForkJoinPool;

/**
 * @author xcy
 * @date 2021/8/16 - 11:39
 */

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * WorkStealingPool分叉组合的线程池
 * 该线程池中每一个线程都有自己的队列
 * WorkStealingPool原理：
 * 多个work queue
 * 采用work stealing算法
 * 本质上是一个ForkJoinPool
 */
public class WorkStealingPoolDemo {
	public static void main(String[] args) {
		ExecutorService service = Executors.newWorkStealingPool();

		//CPU的核的数量
		//System.out.println(Runtime.getRuntime().availableProcessors());

		service.execute(new Run(1000));
		service.execute(new Run(2000));
		service.execute(new Run(2000));
		service.execute(new Run(2000));
		service.execute(new Run(2000));

		try {
			//由于生产的是精灵线程(守护线程、后台线程)，主线程不阻塞的话，看不到输出
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static class Run implements Runnable {
		int time;

		public Run(int time) {
			this.time = time;
		}

		@Override
		public void run() {

		}
	}
}
