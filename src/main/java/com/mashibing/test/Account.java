package com.mashibing.test;

/**
 * @author xcy
 * @date 2021/8/9 - 11:51
 */

import java.util.concurrent.TimeUnit;

/**
 * 面试题：模拟银行账户
 * 对业务写方法加锁
 * 对业务读方法不加锁
 * 这样行不行？
 * 肯定不行
 * 容易产生脏读问题(dirtyRead)
 * 0.0
 * 10000.0
 */
public class Account {
	String name;
	double balance;

	public synchronized void set(String name, double balance) {
		this.name = name;

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		this.balance = balance;
	}

	public double get(String name) {
		return this.balance;
	}

	public static void main(String[] args) {
		Account account = new Account();
		new Thread(() -> {
			account.set("杨幂", 10000.0);
		}, "thread1").start();

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(account.get("杨幂"));//0.0

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(account.get("杨幂"));//10000.0

		/*new Thread(() -> {
			System.out.println(account.get("杨幂"));
		},"thread2").start();*/
	}
}
