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
public class WithoutVolatileAndWaitNotify {
	List<Object> lists = new ArrayList<>();

	public void add(Object obj) {
		lists.add(obj);
	}

	public int size() {
		return lists.size();
	}

	public static void main(String[] args) {
		WithoutVolatileAndWaitNotify withoutVolatile = new WithoutVolatileAndWaitNotify();

		//创建锁定的对象
		final Object lock = new Object();


		new Thread(() -> {
			synchronized (lock) {
				System.out.println("thread2启动");
				if (withoutVolatile.size() != 5) {
					try {
						lock.wait();//notify()回来必须要拿到锁对象，才能继续执行，只是唤醒没有用
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("thread2结束");

				//唤醒线程1，让线程1得到这把锁，然后继续执行
				lock.notify();
			}
		}, "thread2").start();

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		new Thread(() -> {
			synchronized (lock) {
				System.out.println("thread1启动");
				lock.notify();
				for (int i = 0; i < 10; i++) {
					withoutVolatile.add(new Object());
					System.out.println("add" + i);

					if (withoutVolatile.size() == 5) {
						//只是唤醒线程2没有用，因为线程1没有释放锁，线程2就得不到这把锁，因此线程1必须释放锁对象才行
						lock.notify();
						try {
							//只有线程1释放锁，线程2才能得到锁
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				System.out.println("thread1结束");
			}
		}, "thread1").start();

	}
}
