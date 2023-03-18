package com.mashibing.ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author xcy
 * @date 2021/8/16 - 16:26
 */

/**
 * parallelStream并行流
 * 底层使用的是ForkJoinPool
 * 使用场景：
 * 线程之间不需要同步的时候可以使用并行流
 */
public class ParallelStreamAPIDemo {
	public static void main(String[] args) {
		List<Integer> nums = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < 10000; i++) {
			nums.add(1000000 + random.nextInt(1000000));
		}

		long start = System.currentTimeMillis();
		nums.forEach(ParallelStreamAPIDemo::isPrime);
		long end = System.currentTimeMillis();
		System.out.println("ForEach方式执行时间： " + (end - start));

		start = System.currentTimeMillis();
		nums.parallelStream().forEach(ParallelStreamAPIDemo::isPrime);
		end = System.currentTimeMillis();
		System.out.println("parallelStream方式执行时间： " + (end - start));

	}

	/**
	 * 判断number是否是质数
	 * @param integer
	 */
	private static void isPrime(Integer integer) {
		for (int i = 2; i <= integer / 2; i++) {
			if (integer % i == 0) {
				return;
			}
		}
	}
}
