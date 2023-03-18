package com.mashibing.InterviewQuestions1;

/**
 * @author xcy
 * @date 2021/8/11 - 16:13
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 实现一个容器，提供两个方法add、size
 * 写出两个线程：线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数为5时，线程2给出提示并结束
 */
public class WithoutVolatileAndCountDownLatch {
	List<Object> lists = new ArrayList<>();

	public void add(Object obj) {
		lists.add(obj);
	}

	public int size() {
		return lists.size();
	}

	public static void main(String[] args) {
		WithoutVolatileAndCountDownLatch withoutVolatile = new WithoutVolatileAndCountDownLatch();
		//计时器
		CountDownLatch latch = new CountDownLatch(1);
		Thread thread2 = new Thread(() -> {
			System.out.println("thread2启动");
			if (withoutVolatile.size() != 5) {
				try {
					//不满足条件时，线程thread2就等待线程thread1执行
					latch.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("thread2结束");
			latch.countDown();
		}, "thread2");

		Thread thread1 = new Thread(() -> {
			System.out.println("thread1启动");
			for (int i = 0; i < 10; i++) {
				withoutVolatile.add(new Object());
				System.out.println("add : " + i);
				if (withoutVolatile.size() == 5) {
					latch.countDown();
					try {
						latch.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			System.out.println("thread1结束");
		}, "thread1");

		thread2.start();
		thread1.start();
	}
}
