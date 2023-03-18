package com.mashibing.multithread;

/**
 * @author xcy
 * @date 2023/2/27 - 8:13
 */
public class MultiTreadDemo002 {
	public static void main(String[] args) {
		MyThread1 myThread1 = new MyThread1();
		myThread1.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		myThread1.stop = false;

		System.out.println("---------------");


		MyThread2 myThread2 = new MyThread2();
		myThread2.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		myThread2.interrupt();

		System.out.println("---------------");

		MyThread2 myThread3 = new MyThread2();
		myThread3.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		myThread3.interrupt();
	}
}

/**
 * 方式一：通过设置标志位中断线程
 */
class MyThread1 extends Thread {

	boolean stop = true;

	@Override
	public void run() {
		while (stop) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(currentThread().getName() + "正在执行");
		}
	}
}

/**
 * 方式二：通过中断标志位中断线程
 */
class MyThread2 extends Thread {
	@Override
	public void run() {
		while (!Thread.interrupted()) {
			System.out.println(currentThread().getName() + "正在执行");
		}
	}
}

/**
 * 方式三：通过InterruptedException中断线程
 */
class MyThread3 extends Thread {
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
	}
}
