package com.mashibing.volatile_;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xcy
 * @date 2021/8/10 - 9:40
 */

/**
 * volatile只能保证线程的可见性，但是并不能替代synchronized，不能保证线程的原子性
 */
public class VolatileDemo {
	volatile int count = 0;
	public void method() {
		for (int i = 0; i < 10000; i++) {
			count++;
		}
	}

	public static void main(String[] args) {
		VolatileDemo test = new VolatileDemo();

		List<Thread> list =  new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			list.add(new Thread(test::method));
		}

		for (Thread thread : list) {
			thread.start();
		}

		for (Thread thread : list) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println(test.count);
	}
}
