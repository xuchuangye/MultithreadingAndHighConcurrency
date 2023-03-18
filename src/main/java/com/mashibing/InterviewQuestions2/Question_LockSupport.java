package com.mashibing.InterviewQuestions2;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * @author xcy
 * @date 2021/8/14 - 15:56
 */

/**
 * 使用两个线程打印输出A1B2C3...Z26
 * 使用LockSupport的park和unpark方法，百分之百控制thread1先执行或者thread2先执行
 */
public class Question_LockSupport {
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
		thread1 = new Thread(() -> {
			for (Character character : characters) {
				//thread1线程先打印
				System.out.print(character);
				//打印之后，先唤醒thread2线程
				LockSupport.unpark(thread2);
				//如果先park，那么thread1线程先进入阻塞状态，又怎么能唤醒thread2线程呢
				LockSupport.park();
			}
		}, "thread1");

		thread2 = new Thread(() -> {
			for (Integer integer : integers) {
				//thread2线程阻塞
				LockSupport.park();
				//thread2线程打印输出
				System.out.print(integer);
				//打印之后，再唤醒thread1线程
				LockSupport.unpark(thread1);
			}
		}, "thread2");

		thread1.start();
		thread2.start();
	}
}
