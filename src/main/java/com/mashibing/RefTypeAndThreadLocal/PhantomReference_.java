package com.mashibing.RefTypeAndThreadLocal;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author xcy
 * @date 2021/8/13 - 11:38
 */

/**
 * 虚引用
 * 虚引用一般不是给程序员使用的，而是给写JVM虚拟机的人使用的
 */
public class PhantomReference_ {
	private static final List<Object> LIST = new LinkedList<>();

	//队列中全是引用
	private static final ReferenceQueue<Clazz> QUEUE = new ReferenceQueue<>();

	public static void main(String[] args) {
		//参数必须有两个的构造器，而且参数二必须是队列
		PhantomReference<Clazz> ref = new PhantomReference<>(new Clazz(), QUEUE);
		new Thread(() -> {
			while (true) {
				LIST.add(new byte[1024 * 1024]);
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//虚引用中的值是get不到的
				System.out.println(ref.get());
			}
		}).start();

		new Thread(() -> {
			while (true) {
				//QUEUE.poll检测队列
				Reference<? extends Clazz> poll = QUEUE.poll();

				if (poll != null) {
					System.out.println("虚引用对象被回收了 " + poll);
				}
			}
		}).start();

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
