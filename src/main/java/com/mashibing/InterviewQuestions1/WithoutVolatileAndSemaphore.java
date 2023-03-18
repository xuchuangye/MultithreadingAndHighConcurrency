package com.mashibing.InterviewQuestions1;

/**
 * @author xcy
 * @date 2021/8/11 - 16:13
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * 实现一个容器，提供两个方法add、size
 * 写出两个线程：线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数为5时，线程2给出提示并结束
 */
public class WithoutVolatileAndSemaphore {
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
		WithoutVolatileAndSemaphore withoutVolatile = new WithoutVolatileAndSemaphore();

		Semaphore semaphore = new Semaphore(1);
		thread1 = new Thread(() -> {
			try {
				semaphore.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("thread1启动");
			for (int i = 0; i < 5; i++) {
				withoutVolatile.add(new Object());
				System.out.println("add : " + i);
				if (withoutVolatile.size() == 5) {//当线程thread2解除阻塞状态之后，当前线程thread1进入阻塞状态
					//释放许可，解除线程thread2的阻塞状态
					semaphore.release();
				}
			}

			try {
				thread2.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			try {
				semaphore.acquire();
				for (int i = 5; i < 10; i++) {
					System.out.println("add : " + i);
				}
				System.out.println("thread1结束");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});


		thread2 = new Thread(() -> {
			System.out.println("thread2启动");

			if (withoutVolatile.size() != 5) {
				//释放许可之后，进入阻塞状态
				try {
					//直到获取许可之前将一直进入阻塞状态
					semaphore.acquire();
					System.out.println("thread2结束");
					semaphore.release();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//而当线程thread2结束之后，线程thread1就可以继续执行，直到结束
		});

		thread2.start();
		thread1.start();
	}
}
