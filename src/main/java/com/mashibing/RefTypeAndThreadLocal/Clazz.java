package com.mashibing.RefTypeAndThreadLocal;

/**
 * @author xcy
 * @date 2021/8/13 - 9:13
 */
public class Clazz {
	/**
	 * 垃圾回收会调用这个方法
	 * @throws Throwable
	 */
	@Override
	protected void finalize() throws Throwable {
		System.out.println("finalize");
	}
}
