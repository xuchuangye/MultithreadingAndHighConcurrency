package com.mashibing.InterviewQuestions2;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author xcy
 * @date 2021/8/14 - 15:56
 */

/**
 * 使用两个线程打印输出A1B2C3...Z26
 * 使用CountDownLatch的await和countdown方法，
 * 目前有问题
 */
public class Question_CountDownLatch {
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
	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(1);
		thread1 = new Thread(() -> {
			for (Character c : characters) {
				//thread1线程先打印
				System.out.print(c);
				//打印之后，先唤醒thread2线程
				latch.countDown();
				//如果先park，那么thread1线程先进入阻塞状态，又怎么能唤醒thread2线程呢
				try {
					latch.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//latch.countDown();
			}
		}, "thread1");

		thread2 = new Thread(() -> {
			//thread2线程阻塞
			try {
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (Integer integer : integers) {
				//thread2线程打印输出
				System.out.print(integer);

				latch.countDown();
				//打印之后，再唤醒thread1线程
				//latch.countDown();
			}
		}, "thread2");

		thread1.start();
		thread2.start();
	}
}
