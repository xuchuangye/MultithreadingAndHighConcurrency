package com.mashibing.InterviewQuestions1;

/**
 * @author xcy
 * @date 2021/8/11 - 16:13
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 实现一个容器，提供两个方法add、size
 * 写出两个线程：线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数为5时，线程2给出提示并结束
 */
public class WithoutVolatile {
	//没有关键字volatile修饰，会出问题，因为线程2没有得到线程1的通知
	List<Object> lists = new ArrayList<>();

	public void add(Object obj) {
		lists.add(obj);
	}

	public int size() {
		return lists.size();
	}

	public static void main(String[] args) {
		WithoutVolatile withoutVolatile = new WithoutVolatile();

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				withoutVolatile.add(new Object());
				System.out.println("add" + i);
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"thread1").start();

		//线程2会进入死循环，因为线程2没有得到线程1的通知，所以没有break
		new Thread(() -> {
			while (true) {
				if (withoutVolatile.size() == 5) {
					break;
				}
			}
			System.out.println("thread2 end!");
		},"thread2").start();
	}
}
