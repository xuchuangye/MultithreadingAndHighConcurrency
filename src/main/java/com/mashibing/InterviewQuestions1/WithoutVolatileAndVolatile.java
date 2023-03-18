package com.mashibing.InterviewQuestions1;

/**
 * @author xcy
 * @date 2021/8/11 - 16:13
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 实现一个容器，提供两个方法add、size
 * 写出两个线程：线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数为5时，线程2给出提示并结束
 */
public class WithoutVolatileAndVolatile {
	//添加volatile关键字，使得线程2能够得到通知，但是如果将sleep去除，仍然会出现线程2死循环的问题
	volatile List<Object> lists = new ArrayList<>();
	//即使是使用同步容器synchronizedList，仍然会出现线程2获取不到线程1的值，无法break，进入死循环
	//volatile List<Object> lists = Collections.synchronizedList(new LinkedList<>());

	public /*synchronized*/ void add(Object obj) {
		lists.add(obj);
	}

	public /*synchronized*/ int size() {
		return lists.size();
	}

	public static void main(String[] args) {
		WithoutVolatileAndVolatile withoutVolatile = new WithoutVolatileAndVolatile();

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				withoutVolatile.add(new Object());
				System.out.println("add" + i);
				/*try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}*/
			}
		},"thread1").start();

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
