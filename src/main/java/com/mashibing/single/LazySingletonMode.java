package com.mashibing.single;

/**
 * @author xcy
 * @date 2021/8/10 - 8:51
 */

import java.util.concurrent.TimeUnit;

/**
 * 懒汉式单例模式之双重检查
 * 需要返回的实例需不需要加上volatile关键字？
 * 需要，因为可以有效地避免指令重排序的问题，保证了返回的实例一定经过了完整的初始化过程之后才会返回，防止发生不必要的错误
 */
public class LazySingletonMode {
	//不加volatile关键字，会出现指令重排序的问题，导致不可预见的错误
	//
	private static volatile LazySingletonMode INSTANCE;
	private LazySingletonMode() {

	}
	public static LazySingletonMode getINSTANCE() {
		if (INSTANCE == null) {
			synchronized (LazySingletonMode.class) {
				if (INSTANCE == null) {

					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					INSTANCE = new LazySingletonMode();
					/*
					* JVM虚拟机new对象编译器编译完成之后，指令分为三步：
					* 第一步：给该对象申请内存
					* 第二步：给该对象的成员变量初始化
					* 第三步：将申请到的内存当中包含的内容赋值给INSTANCE变量
					* */
				}
			}
		}
		return INSTANCE;
	}

	public static void main(String[] args) {
		/*LazySingletonMode instance1 = LazySingletonMode.getInstance();
		LazySingletonMode instance2 = LazySingletonMode.getInstance();
		System.out.println(instance1 == instance2);*/

		for (int i = 0; i < 100; i++) {
			new Thread(() -> {
				System.out.println(LazySingletonMode.getINSTANCE().hashCode());
			}).start();
		}
	}
}
