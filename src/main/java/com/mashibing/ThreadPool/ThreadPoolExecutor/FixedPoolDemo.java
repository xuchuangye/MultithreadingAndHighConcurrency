package com.mashibing.ThreadPool.ThreadPoolExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author xcy
 * @date 2021/8/15 - 16:00
 */

/**
 * FixedThreadPool
 * 使用固定的线程数可以并行
 */
public class FixedPoolDemo {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		getPrime(1,200000);
		long end = System.currentTimeMillis();
		System.out.println("花费时间：" + (end - start));

		//CPU处理器的核心数
		//System.out.println(Runtime.getRuntime().availableProcessors());//4
		final int cpuCoreNum = 4;
		ExecutorService service = Executors.newFixedThreadPool(cpuCoreNum);
		MyTask t1 = new MyTask(1,80000);
		MyTask t2 = new MyTask(80001,130000);
		MyTask t3 = new MyTask(130001,170000);
		MyTask t4 = new MyTask(170001,200000);

		Future<List<Integer>> future1 = service.submit(t1);
		Future<List<Integer>> future2 = service.submit(t2);
		Future<List<Integer>> future3 = service.submit(t3);
		Future<List<Integer>> future4 = service.submit(t4);

		start = System.currentTimeMillis();
		try {
			future1.get();
			future2.get();
			future3.get();
			future4.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		end = System.currentTimeMillis();

		System.out.println("花费时间：" + (end - start));

		service.shutdown();
	}
	
	private static List<Integer> getPrime(int start, int end) {
		List<Integer> results = new ArrayList<>();
		for (int i = start; i <= end; i++) {
			if (isPrime(i)) {
				results.add(i);
			}
		}
		return results;
	}

	private static boolean isPrime(int num) {
		for (int i = 2; i <= num / 2; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

	static class MyTask implements Callable<List<Integer>> {
		int startPos,endPos;

		public MyTask(int start, int end) {
			this.startPos = start;
			this.endPos = end;
		}

		@Override
		public List<Integer> call() throws Exception {
			List<Integer> list = getPrime(startPos,endPos);
			return list;
		}
	}
}
