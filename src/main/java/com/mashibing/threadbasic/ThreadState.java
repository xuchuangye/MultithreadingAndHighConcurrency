package com.mashibing.threadbasic;

/**
 * @author xcy
 * @date 2021/8/18 - 17:45
 */


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试线程的状态
 */
public class ThreadState {
	public static void main(String[] args) {
		//-----------------------------------thread1----------------------------------------
		Thread thread1 = new Thread(() -> {
			System.out.println("2:" + Thread.currentThread().getState());
			for (int i = 0; i < 3; i++) {
				SleepHelper.sleepSeconds(1);
				System.out.println(i + " ");
			}
			System.out.println();
		});
		//start方法之前，肯定是NEW状态
		System.out.println("1:" + thread1.getState());
		//调用start启动之后处于RUNNABLE状态
		thread1.start();

		try {
			thread1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//thread1线程结束之后，处于TERMINATED状态
		System.out.println("3:" + thread1.getState());


		//------------------------------thread2------------------------------
		Thread thread2 = new Thread(() -> {
			LockSupport.park();
			System.out.println("thread2 go on!");
			SleepHelper.sleepSeconds(5);
		});

		//thread2启动之后调用park方法就处于阻塞状态
		thread2.start();
		SleepHelper.sleepSeconds(1);
		//处于阻塞状态就是WAITING状态
		System.out.println("4:" + thread2.getState());
		//unpark方法解除阻塞状态
		LockSupport.unpark(thread2);
		SleepHelper.sleepSeconds(1);
		//解除阻塞状态，执行结束应该处于TERMINATED状态，但是由于又调用了sleep方法，所以处于TIMED_WAITING状态
		System.out.println("5:" + thread2.getState());


		//------------------------------thread3------------------------------
		final Object obj = new Object();
		Thread thread3 = new Thread(() -> {
			synchronized (obj) {
				System.out.println("thread3得到了锁");
			}
		});

		new Thread(() -> {
			synchronized (obj) {
				SleepHelper.sleepSeconds(5);
			}
		}).start();

		SleepHelper.sleepSeconds(1);
		//启动thread3线程之后，因为得不到锁对象obj，所以处于BLOCKED状态
		thread3.start();
		SleepHelper.sleepSeconds(1);
		System.out.println("6:" + thread3.getState());

		//------------------------------thread4------------------------------
		//ReentrantLock使用的是JUC的锁，JUC的锁都是CAS实现，CAS实现的是忙等待，进入WAITING状态
		ReentrantLock lock = new ReentrantLock();
		Thread thread4 = new Thread(() -> {
			lock.lock();
			System.out.println("thread4得到了锁");
			lock.unlock();
		});

		new Thread(() -> {
			lock.lock();
			SleepHelper.sleepSeconds(5);
			lock.unlock();
		}).start();

		SleepHelper.sleepSeconds(1);
		thread4.start();
		SleepHelper.sleepSeconds(1);
		System.out.println("7:" + thread4.getState());
		//------------------------------thread5------------------------------
		//LockSupport.park()使用的是JUC的锁，JUC的锁都是CAS实现，CAS实现的是忙等待，进入WAITING状态
		Thread thread5 = new Thread(LockSupport::park);
		thread5.start();
		SleepHelper.sleepSeconds(1);
		System.out.println("8:" + thread5.getState());
		LockSupport.unpark(thread5);
		System.out.println("thread5得到了这把锁");
	}
}
