package com.mashibing.InterviewQuestions2;

import java.util.LinkedList;
import java.util.List;

/**
 * @author xcy
 * @date 2021/8/14 - 15:56
 */

/**
 * 使用两个线程打印输出A1B2C3...Z26
 * 使用自定义自旋锁的方式，百分之百控制thread1先执行或者thread2先执行
 */
public class Question_CAS {
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

	enum ReadyToRun {
		T1, T2;
	}

	//volatile保证线程之间的可见性
	static volatile ReadyToRun readyToRun = ReadyToRun.T1;

	public static void main(String[] args) {
		thread1 = new Thread(() -> {
			for (Character c : characters) {
				//默认开始的时候是T1，所以不会进此循环
				while (readyToRun != ReadyToRun.T1) {}
				//thread1线程打印输出
				System.out.print(c);
				//将T1修改为T2，下次for循环遍历会进入while死循环，等待其它线程执行
				readyToRun = ReadyToRun.T2;
			}
		}, "thread1");

		thread2 = new Thread(() -> {
			for (Integer integer : integers) {
				//而当T1修改T2时，因为volatile保证了其它线程可见，所以此时thread2线程发现了T1已经修改为T2，因此不会进入while死循环
				while (readyToRun != ReadyToRun.T2) {}
				//thread2线程打印输出
				System.out.print(integer);
				//输出之后将T2修改为T1，下次for循环遍历会进入while死循环，等待其它线程执行
				readyToRun = ReadyToRun.T1;
			}
		}, "thread2");

		thread1.start();
		thread2.start();
	}
}
