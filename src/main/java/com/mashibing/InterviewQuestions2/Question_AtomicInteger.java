package com.mashibing.InterviewQuestions2;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xcy
 * @date 2021/8/14 - 15:56
 */

/**
 * 使用两个线程打印输出A1B2C3...Z26
 * 使用LockSupport的park和unpark方法，百分之百控制thread1先执行或者thread2先执行
 */
public class Question_AtomicInteger {
	static Thread thread1 = null;
	static Thread thread2 = null;

	static List<Integer> integers = new LinkedList<>();
	static List<Character> characters = new LinkedList<>();

	static {
		for (int i = 1; i <= 26; i++) {
			integers.add(i);
		}
		for (char c = 'A'; c <= 'Z'; c++) {
			characters.add(c);
		}
	}

	static AtomicInteger integerAtomic = new AtomicInteger(1);

	public static void main(String[] args) {
		thread1 = new Thread(() -> {
			for (Character c : characters) {
				while (integerAtomic.get() != 1) {}
				System.out.print(c);
				integerAtomic.set(2);
			}
		}, "thread1");

		thread2 = new Thread(() -> {
			for (Integer integer : integers) {
				while (integerAtomic.get() != 2){}
				System.out.print(integer);
				integerAtomic.set(1);
			}
		}, "thread2");

		thread1.start();
		thread2.start();
	}
}
