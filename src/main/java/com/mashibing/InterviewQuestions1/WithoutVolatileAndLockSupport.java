package com.mashibing.InterviewQuestions1;

/**
 * @author xcy
 * @date 2021/8/11 - 16:13
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * 实现一个容器，提供两个方法add、size
 * 写出两个线程：线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数为5时，线程2给出提示并结束
 */
public class WithoutVolatileAndLockSupport {
	List<Object> lists = new ArrayList<>();

	public void add(Object obj) {
		lists.add(obj);
	}

	public int size() {
		return lists.size();
	}

	static Thread thread1 = null;
	static Thread thread2 = null;
	public static void main(String[] args) {
		WithoutVolatileAndLockSupport withoutVolatile = new WithoutVolatileAndLockSupport();


		thread1 = new Thread(() -> {
			System.out.println("thread1启动");
			for (int i = 0; i < 10; i++) {
				withoutVolatile.add(new Object());
				System.out.println("add : " + i);

				if (withoutVolatile.size() == 5) {
					LockSupport.unpark(thread2);

					//这句话至关重要
					LockSupport.park();
				}

				/*try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}*/
			}

			System.out.println("thread1结束");
		});


		thread2 = new Thread(() -> {
			System.out.println("thread2启动");
			//if (withoutVolatile.size() != 5) {
				LockSupport.park();
			//}
			System.out.println("thread2结束");
			//而当线程thread2结束之后，线程thread1就可以继续执行，直到结束
			LockSupport.unpark(thread1);
		});

		thread2.start();
		thread1.start();
	}
}
