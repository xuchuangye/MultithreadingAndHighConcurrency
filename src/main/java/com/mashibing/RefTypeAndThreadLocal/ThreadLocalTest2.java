package com.mashibing.RefTypeAndThreadLocal;

import java.util.concurrent.TimeUnit;

/**
 * @author xcy
 * @date 2021/8/13 - 8:26
 */

/**
 * ThreadLocal用于声明式事务，保证同一个Connection
 */
public class ThreadLocalTest2 {
	volatile static ThreadLocal<Human> p = new ThreadLocal<>();

	public static void main(String[] args) {
		Thread thread1 = new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(p.get());
		});

		Thread thread2 = new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//只有当前线程能读取到，其它线程读取不到
			p.set(new Human());
			System.out.println(p.get());//Human{name='zhangsan'}
		});
		thread1.start();
		thread2.start();
	}
	static class Human {
		String name = "zhangsan";

		@Override
		public String toString() {
			return "Human{" +
					"name='" + name + '\'' +
					'}';
		}
	}
}

