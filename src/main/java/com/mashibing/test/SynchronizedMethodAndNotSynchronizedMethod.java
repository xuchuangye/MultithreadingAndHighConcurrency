package com.mashibing.test;

/**
 * @author xcy
 * @date 2021/8/9 - 11:34
 */

/**
 * 同步方法和非同步方法是否可以同时调用
 * thread1syncMethod ... start
 * thread2notSyncMethod
 * thread1syncMethod ... end
 * 根据结果显示，可以判断同步方法和费同步方法可以同时调用
 */
public class SynchronizedMethodAndNotSynchronizedMethod {
	public synchronized void syncMethod() {
		System.out.println(Thread.currentThread().getName() + "syncMethod ... start");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "syncMethod ... end");
	}
	public void notSyncMethod() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "notSyncMethod");
	}

	public static void main(String[] args) {
		SynchronizedMethodAndNotSynchronizedMethod synchronizedMethod = new SynchronizedMethodAndNotSynchronizedMethod();


		//函数引用
		new Thread(synchronizedMethod::syncMethod,"thread1").start();
		new Thread(synchronizedMethod::notSyncMethod,"thread2").start();
		//lambda
		/*new Thread(() -> synchronizedMethod.syncMethod(),"thread1").start();
		new Thread(() -> synchronizedMethod.notSyncMethod(),"thread2").start();*/
		//1.8之前的写法
		/*new Thread(new Runnable() {
			@Override
			public void run() {
				synchronizedMethod.syncMethod();
			}
		},"thread1").start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				synchronizedMethod.notSyncMethod();
			}
		},"thread2").start();*/
	}
}
