package com.mashibing.RefTypeAndThreadLocal;

/**
 * @author xcy
 * @date 2021/8/13 - 9:16
 */

import java.io.IOException;

/**
 * 普通引用，也就是强引用
 * 当没有引用指向该对象的话，该对象就会被回收
 */
public class NormalReference {
	public static void main(String[] args) throws IOException {
		Clazz clazz = new Clazz();
		clazz = null;

		//gc方法是在别的线程执行的
		System.gc();

		//因此需要阻塞当前线程
		System.in.read();
	}
}
