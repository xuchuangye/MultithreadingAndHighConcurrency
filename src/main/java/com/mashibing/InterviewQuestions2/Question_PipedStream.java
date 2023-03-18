package com.mashibing.InterviewQuestions2;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xcy
 * @date 2021/8/14 - 15:56
 */

/**
 * 使用两个线程打印输出A1B2C3...Z26
 * 使用PipedInputStream和PipedOutputStream的read和write方法，而且都是阻塞的方法
 */
public class Question_PipedStream {
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
		final PipedInputStream input1 = new PipedInputStream();
		final PipedInputStream input2 = new PipedInputStream();
		final PipedOutputStream output1 = new PipedOutputStream();
		final PipedOutputStream output2 = new PipedOutputStream();
		try {
			input1.connect(output1);
			input2.connect(output2);

			String msg = "Your Turn";

			thread1 = new Thread(() -> {
				byte[] buffer = new byte[9];
				for (Character character : characters) {
					try {
						input1.read(buffer);
						if (new String(buffer).equals(msg)) {
							//thread1线程先打印
							System.out.print(character);
						}
						output1.write(msg.getBytes());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}, "thread1");

			thread2 = new Thread(() -> {
				byte[] buffer = new byte[9];
				for (Integer integer : integers) {

					//thread2线程打印输出
					System.out.print(integer);

					try {
						output2.write(msg.getBytes());

						input2.read(buffer);
					} catch (IOException e) {
						e.printStackTrace();
					}

					if (new String(buffer).equals(msg)) {
						continue;
					}
				}
			}, "thread2");

			thread1.start();
			thread2.start();
		} catch (IOException e) {
			e.printStackTrace();
		} /*finally {
			try {
				input1.close();
				input2.close();
				output1.close();
				output2.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/

	}
}
