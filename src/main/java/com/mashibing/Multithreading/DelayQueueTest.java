package com.mashibing.Multithreading;

/**
 * @author xcy
 * @date 2021/8/14 - 11:04
 */

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * DelayQueue类是BlockingQueue的实现类
 * 按时间进行优先级的排列
 * 使用场景：按时间进行任务调度
 */
public class DelayQueueTest {
	static BlockingQueue<MyTask> tasks = new DelayQueue<MyTask>();

	static Random random = new Random();

	/**
	 * 任务类，实现往后拖延、推迟的类
	 */
	static class MyTask implements Delayed {
		//任务名
		String name;
		//推迟多长时间
		long runningTime;

		public MyTask(String name, long runningTime) {
			this.name = name;
			this.runningTime = runningTime;
		}

		/**
		 * 得到推迟的时间
		 *
		 * @param unit 时间单位
		 * @return 返回
		 */
		@Override
		public long getDelay(TimeUnit unit) {
			return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
		}

		/**
		 * 根据时间进行比较
		 *
		 * @param o 推迟的对象
		 * @return 返回
		 */
		@Override
		public int compareTo(Delayed o) {
			/*if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
				return -1;
			} else if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
				return 1;
			} else {
				return 0;
			}*/
			return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
		}

		@Override
		public String toString() {
			return name + " : " + runningTime;
		}
	}

	public static void main(String[] args) {
		long now = System.currentTimeMillis();

		//按照指定的时间执行任务
		MyTask task1 = new MyTask("1", now + 1000);
		MyTask task2 = new MyTask("2", now + 2000);
		MyTask task3 = new MyTask("3", now + 1500);
		MyTask task4 = new MyTask("4", now + 2500);
		MyTask task5 = new MyTask("5", now + 500);

		try {
			tasks.put(task1);
			tasks.put(task2);
			tasks.put(task3);
			tasks.put(task4);
			tasks.put(task5);
			System.out.println(tasks);

			for (int i = 0; i < 5; i++) {
				//得到的结果根据时间的长短进行打印输出
				System.out.println(tasks.take());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
