package com.mashibing.synchronized_;

import java.util.concurrent.TimeUnit;

/**
 * @author xcy
 * @date 2021/8/10 - 10:33
 */

/**
 * 锁定摸个对象obj，那么如果Obj的属性发生改变，不会影响锁的使用
 * 但是如果该obj对象发生改变，则锁定的对象也会发生改变
 * 所以应当避免将锁定的对象的引用变成别的对象
 */
public class ChangeObject {
	//最好的办法就是讲锁定的对象加上final关键字，之后该对象永远都不可能发生改变
	final Object obj = new Object();

	public void method() {
		synchronized (obj) {
			while (true) {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			}

		}
	}

	public static void main(String[] args) {
		ChangeObject object = new ChangeObject();
		Thread thread1 = new Thread(object::method, "Thread1");
		thread1.start();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Thread thread2 = new Thread(object::method, "Thread2");
		//锁定的对象发生的改变，所以Thread2线程得到执行的机会，如果注释掉这句代码，则Thread2永远得不到执行的机会
		//object.obj = new Object();
		thread2.start();

	}
}
