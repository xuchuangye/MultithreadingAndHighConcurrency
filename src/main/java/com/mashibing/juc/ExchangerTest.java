package com.mashibing.juc;

/**
 * @author xcy
 * @date 2021/8/11 - 11:05
 */

import java.util.concurrent.Exchanger;

/**
 * 交换器
 */
public class ExchangerTest {
	static Exchanger<String> exchanger = new Exchanger<>();

	public static void main(String[] args) {
		new Thread(() -> {
			String string = "Thread1";
			try {
				//当第一个线程执行exchange方法时，必须要有第二个线程也执行exchange方法，否则第一个线程会一直处于阻塞状态
				string = exchanger.exchange(string);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " : " + string);
		}, "thread1").start();

		new Thread(() -> {
			String string = "Thread2";
			try {
				string = exchanger.exchange(string);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " : " + string);
		}, "thread2").start();
	}
}
