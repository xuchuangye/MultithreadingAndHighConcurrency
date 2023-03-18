package com.mashibing.juc;

import java.util.ArrayList;
import java.util.List;


//Atomic开头的，都是使用CAS这种操作来保证线程安全的类
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xcy
 * @date 2021/8/10 - 10:56
 */

/**
 * 使用CAS无锁操作实现的
 */
public class AtomicInteger_ {
	/*volatile int count = 0;*/
	AtomicInteger count = new AtomicInteger(0);

	/*synchronized*/ void method() {
		for (int i = 0; i < 10000; i++) {
			//increment表示自增 get表示获取该值
			count.incrementAndGet();//相当于count++，但这句代码是线程安全的
			//count++;count++不是线程安全的
		}
	}

	public static void main(String[] args) {
		AtomicInteger_ integer = new AtomicInteger_();

		List<Thread> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(new Thread(integer::method,"Thread " + i));
		}

		for (Thread thread : list) {
			thread.start();
			System.out.println(thread.getName());
		}

		for (Thread thread : list) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println(integer.count);


	}
}
