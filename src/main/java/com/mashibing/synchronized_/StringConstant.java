package com.mashibing.synchronized_;

/**
 * @author xcy
 * @date 2021/8/10 - 10:44
 */

/**
 * 不要使用String常量作为锁的对象
 */
public class StringConstant {
	String str1 = "Hello";
	String str2 = "Hello";
	void method1() {
		synchronized (str1) {
			System.out.println(Thread.currentThread().getName());
		}
	}
	void method2() {
		synchronized (str2) {
			System.out.println(Thread.currentThread().getName());
		}
	}

	public static void main(String[] args) {
		StringConstant stringConstant = new StringConstant();
		new Thread(stringConstant::method1).start();
		new Thread(stringConstant::method2).start();
	}
}
