package com.mashibing.RefTypeAndThreadLocal;

import java.util.concurrent.TimeUnit;

/**
 * @author xcy
 * @date 2021/8/13 - 8:26
 */
public class ThreadLocalTest {
	volatile static Person p = new Person();

	public static void main(String[] args) {
		new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(p.name);
		}).start();

		new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			p.name = "lisi";
		}).start();
	}
	static class Person {
		String name = "zhangsan";
	}
}

