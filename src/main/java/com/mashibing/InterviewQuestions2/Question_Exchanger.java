package com.mashibing.InterviewQuestions2;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * @author xcy
 * @date 2021/8/14 - 15:56
 */

/**
 * 使用两个线程打印输出A1B2C3...Z26
 * 使用Exchanger的exchange方法交换两个线程之间的数据
 */
public class Question_Exchanger {
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

	private static final Exchanger<String> exchanger = new Exchanger<>();

	public static void main(String[] args) {
		thread1 = new Thread(() -> {
			for (Character character : characters) {
				System.out.print(character);
				try {
					exchanger.exchange("thread2");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "thread1");

		thread2 = new Thread(() -> {
			for (Integer integer : integers) {
				try {
					exchanger.exchange("thread1");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.print(integer);
			}
		}, "thread2");

		thread1.start();
		thread2.start();
	}
}
