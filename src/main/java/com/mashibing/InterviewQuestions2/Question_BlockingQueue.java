package com.mashibing.InterviewQuestions2;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author xcy
 * @date 2021/8/14 - 15:56
 */

/**
 * 使用两个线程打印输出A1B2C3...Z26
 * 使用BlockingQueue的put和take方法，百分之百控制thread1先执行或者thread2先执行
 */
public class Question_BlockingQueue {
	static Thread thread1 = null;
	static Thread thread2 = null;

	static List<Integer> integers = new LinkedList<>();
	static List<Character> characters = new LinkedList<>();

	static BlockingQueue<String> blockingQueue1 = new ArrayBlockingQueue<String>(1);
	static BlockingQueue<String> blockingQueue2 = new ArrayBlockingQueue<String>(1);

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
				//thread1线程先打印输出,控制thread1线程先执行
				System.out.print(character);
				try {
					//put方法，如果满了就进入阻塞状态，没有满，则继续执行
					blockingQueue1.put("ok");
					//take获取，如果获取失败，就进入阻塞状态
					blockingQueue2.take();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "thread1");

		thread2 = new Thread(() -> {
			for (Integer integer : integers) {
				//blockingQueue1调用take方法如果获取不到值进入阻塞状态，如果获取到值，就会继续执行
				try {
					blockingQueue1.take();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//thread2线程打印输出，控制thread1线程先执行
				System.out.print(integer);
				//打印之后，blockingQueue2再次添加数据，没有满则继续执行
				try {
					blockingQueue2.put("ok");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "thread2");

		thread1.start();
		thread2.start();
	}
}
