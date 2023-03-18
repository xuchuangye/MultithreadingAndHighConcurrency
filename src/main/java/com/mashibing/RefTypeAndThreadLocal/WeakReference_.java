package com.mashibing.RefTypeAndThreadLocal;

/**
 * @author xcy
 * @date 2021/8/13 - 10:10
 */

import java.lang.ref.WeakReference;

/**
 * 弱引用
 * 如果一个对象只具有弱引用，那么垃圾回收器在扫描到该对象时，无论内存充足与否，都会回收该对象的内存
 * 只要遭遇到System.gc()就会回收
 * 弱引用一般用于容器
 */
public class WeakReference_ {
	public static void main(String[] args) {
		WeakReference<Clazz> ref = new WeakReference<>(new Clazz());

		//第一次打印肯定会打印出地址
		System.out.println(ref.get());
		System.gc();
		//第二次因为gc垃圾回收机制，所以肯定为null
		System.out.println(ref.get());

		Clazz clazz = new Clazz();
		ThreadLocal<Clazz> threadLocal = new ThreadLocal<>();
		threadLocal.set(clazz);
		//clazz = null;
		//使用ThreadLocal必须要调用remove方法，否则仍然会存在内存泄漏的问题
		threadLocal.remove();
	}
}
