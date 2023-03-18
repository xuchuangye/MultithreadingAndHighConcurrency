package com.mashibing.ThreadPool.ForkJoinPool;

/**
 * @author xcy
 * @date 2021/8/16 - 15:24
 */

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;

/**
 *
 */
public class ForkJoinPoolDemo {
	static int[] nums = new int[1000000];
	static final int MAX_NUM = 50000;
	static Random random = new Random();
	
	static {
		for (int i = 0; i < nums.length; i++) {
			nums[i] = random.nextInt(100);
		}
		//System.out.println(Arrays.stream(nums).sum());
	}

	static class AddTask extends RecursiveAction {
		int start,end;

		public AddTask(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		protected void compute() {
			if (end - start <= MAX_NUM) {
				long sum = 0L;
				for (int j = start; j < end; j++) {
					sum += nums[j];
				}
				System.out.println("form:" + start + " to:" + end + " = " + sum);
			}else {
				int middle = start + (end - start) /2;
				AddTask subTask1 = new AddTask(start, middle);
				AddTask subTask2 = new AddTask(middle, end);
				subTask1.fork();
				subTask2.fork();
			}
		}
	}

	static class AddTaskReturn extends RecursiveTask<Long> {
		int start,end;

		public AddTaskReturn(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		protected Long compute() {
			if (end - start <= MAX_NUM) {
				long sum = 0L;
				for (int j = start; j < end; j++) {
					sum += nums[j];
				}
				return sum;
			}else {
				int middle = start + (end - start) /2;
				AddTaskReturn subTask1 = new AddTaskReturn(start, middle);
				AddTaskReturn subTask2 = new AddTaskReturn(middle, end);
				subTask1.fork();
				subTask2.fork();

				return subTask1.join() + subTask2.join();
			}
		}
	}
	
	public static void main(String[] args) {
		ForkJoinPool threadPool = new ForkJoinPool();
		AddTask addTask = new AddTask(0, nums.length);
		//因为AddTask类继承的RecursiveAction类没有返回值，所以只能使用execute方法，该方法没有返回值
		threadPool.execute(addTask);
		threadPool.shutdown();

		/*ForkJoinPool forkJoinPool = new ForkJoinPool();
		AddTaskReturn task = new AddTaskReturn(0,nums.length);
		//因为AddTaskReturn类继承的RecursiveTask<Long>类具有返回值，所以可以使用submit方法，该方法具有返回值
		ForkJoinTask<Long> submit = forkJoinPool.submit(task);
		try {
			System.out.println(submit.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		forkJoinPool.shutdown();*/
		/*try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
}
