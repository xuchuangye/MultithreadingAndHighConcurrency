package com.mashibing.RefTypeAndThreadLocal;

/**
 * @author xcy
 * @date 2021/8/13 - 9:22
 */

import java.lang.ref.SoftReference;

/**
 * 软引用
 * 当一个对象被软引用指向的时候，只有系统内存不够用时，才会回收该对象
 * 软引用主要用于缓存
 */
public class SoftReference_ {
	public static void main(String[] args) {
		SoftReference<byte[]> ref = new SoftReference<byte[]>(new byte[1024 * 1024 * 10]);

		//ref = null;
		//此时内存够用，第一次可以打印出地址
		System.out.println(ref.get());
		//因为此时的内存够用，垃圾回收机制不会回收
		System.gc();

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//因为垃圾回收机制没有回收内存，所以第二次可以打印出地址
		System.out.println(ref.get());
		//再分配一个数组，heap将装不下，此时 系统将会垃圾回收，先回收一次，如果不够，系统会把软引用干掉
		byte[] bytes = new byte[1024 * 1024 * 15];
		//此时内存已经不够使用了，该软引用会被垃圾回收机制回收
		System.out.println(ref.get());
	}
}
