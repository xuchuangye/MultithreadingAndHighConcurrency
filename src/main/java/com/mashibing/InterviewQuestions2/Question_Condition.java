package com.mashibing.InterviewQuestions2;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xcy
 * @date 2021/8/14 - 15:56
 */

/**
 * 使用两个线程打印输出A1B2C3...Z26
 * 使用Condition的signal和await方法，不能百分之百控制thread1先执行或者thread2先执行
 */
public class Question_Condition {
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

	public static void main(String[] args) throws InterruptedException {

		CountDownLatch latch = new CountDownLatch(1);
		ReentrantLock lock = new ReentrantLock();

		//Condition本质上是等待队列
		Condition condition1 = lock.newCondition();
		Condition condition2 = lock.newCondition();
		thread1 = new Thread(() -> {

			try {
				lock.lock();

				for (Character character : characters) {
					//thread1线程先打印
					System.out.print(character);
					latch.countDown();
					//打印之后，先唤醒thread2线程
					condition2.signal();
					//如果先park，那么thread1线程先进入阻塞状态，又怎么能唤醒thread2线程呢
					condition1.await();
				}

				//执行到最后肯定有一个线程处于await阻塞状态，所以肯定需要去唤醒另外一个线程
				condition2.signal();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}, "thread1");

		thread2 = new Thread(() -> {

			try {
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			try {
				lock.lock();
				for (Integer integer : integers) {
					//thread2线程打印输出
					System.out.print(integer);
					//打印之后，再唤醒thread1线程
					condition1.signal();//notify
					//thread2线程阻塞
					condition2.await();
				}

				//执行到最后肯定有一个线程处于await阻塞状态，所以肯定需要去唤醒另外一个线程
				condition1.signal();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}, "thread2");

		thread1.start();
		thread2.start();
	}
}
