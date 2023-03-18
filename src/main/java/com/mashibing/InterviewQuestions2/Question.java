package com.mashibing.InterviewQuestions2;

/**
 * @author xcy
 * @date 2021/8/12 - 9:42
 */

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 要求用线程顺序打印A1B2C3...Z26
 */
public class Question {
	static Queue<Integer> integerQueue = new ConcurrentLinkedQueue<Integer>();
	static Queue<Character> characterQueue = new ConcurrentLinkedQueue<Character>();

	static {
		for (int i = 1; i <= 26; i++) {
			integerQueue.add(i);
		}
		for (char c = 'A'; c <= 'Z'; c++) {
			characterQueue.add(c);
		}
	}

	static Thread thread1 = null;
	static Thread thread2 = null;

	public static void main(String[] args) {
		thread1 = new Thread(() -> {
			for (int i = 0; i < integerQueue.size(); i++) {
				System.out.print(integerQueue.poll());
			}

		});
		thread2 = new Thread(() -> {
			for (char c : characterQueue) {
				System.out.print(characterQueue.poll());
			}
		});

		thread2.start();
		thread1.start();

	}
}
