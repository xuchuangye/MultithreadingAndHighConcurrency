package com.mashibing.Visibility;

import com.mashibing.threadbasic.SleepHelper;

/**
 * @author xcy
 * @date 2021/8/19 - 16:00
 */

/**
 * volatile修饰引用类型
 * volatile修饰保证了引用类型的地址可见，不能保证引用类型的地址所对应的值可见
 */
public class VolatileReference {
	static class InnerClass {
		static boolean running = true;

		public InnerClass() {
		}


		public static void method() {
			System.out.println("method start");
			while(running) {

			}
			System.out.println("method end");
		}
	}
	private static volatile InnerClass clazz = new InnerClass();

	public static void main(String[] args) {
		new Thread(InnerClass::method,"thread").start();

		SleepHelper.sleepSeconds(1);

		InnerClass.running = false;
	}
}
