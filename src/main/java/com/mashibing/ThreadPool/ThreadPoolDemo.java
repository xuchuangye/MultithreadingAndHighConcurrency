package com.mashibing.ThreadPool;

/**
 * @author xcy
 * @date 2021/8/15 - 10:51
 */

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPool线程池
 * ThreadPool维护两个集合
 * 线程的集合
 * 任务的集合
 */
public class ThreadPoolDemo {
	static class Task implements Runnable {
		private int i;

		public Task(int i) {
			this.i = i;
		}

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " : Task - " + i);
			try {
				System.in.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public String toString() {
			return "Task{" +
					"i=" + i +
					'}';
		}

		public static void main(String[] args) {
			ThreadPoolExecutor tpe = new ThreadPoolExecutor(
					//核心线程数
					2,
					//最大线程数，如果线程不够了，最多能扩展多少的线程数
					4,
					//生存时间，如果线程长时间没有使用，需要归还给操作系统，核心线程不会归还给操作系统
					60,
					//生存时间的时间单位
					TimeUnit.SECONDS,
					//任务队列
					new ArrayBlockingQueue<Runnable>(4),
					//线程工厂
					Executors.defaultThreadFactory(),
					//拒绝策略
					//默认的拒绝策略是AbortPolicy，会抛出异常
					//new ThreadPoolExecutor.AbortPolicy()
					//DiscardPolicy表示不抛出异常
					//new ThreadPoolExecutor.DiscardPolicy()
					//DiscardOldestPolicy表示扔出先执行时间最长的任务，然后重新执行该任务
					new ThreadPoolExecutor.DiscardOldestPolicy()
					//CallerRunsPolicy表示当前线程池已满，由调用线程处理该任务
					//new ThreadPoolExecutor.CallerRunsPolicy()
					);

			//拒绝策略执行过程
			//刚开始线程池是空的，当第一个任务进来时，创建一个核心线程，
			//当第二个任务进来时，第一个核心线程正忙时，创建第二个核心线程
			//当第三个任务进来时，此时第一和第二核心线程都在忙时，该线程池会进入阻塞队列
			//当第四个任务到第六个任务进来时 此时第一和第二核心线程都在忙时，该线程池会进入阻塞队列
			//当第七个任务进来时，此时第一和第二核心线程都在忙时以及阻塞队列已满，该线程池会创建第三个非核心线程
			//当第八个任务进来时，此时第一和第二核心线程都在忙时以及阻塞队列已满，该线程池会创建第四个非核心线程
			//当第九个任务进来时，此时第一和第二核心线程都在忙时，并且阻塞队列已满，并且第三个和第四个非核心线程都在忙，那么就会执行拒绝策略
			for (int i = 0; i < 8; i++) {
				tpe.execute(new Task(i));
			}

			System.out.println(tpe.getQueue());
			tpe.execute(new Task(100));
			System.out.println(tpe.getQueue());
			tpe.shutdown();
		}
	}
}
