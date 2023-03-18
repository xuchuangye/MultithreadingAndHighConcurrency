package com.mashibing.single;

/**
 * @author xcy
 * @date 2021/8/10 - 8:47
 */

/**
 * 饿汉式单例模式
 */
public class HungryHanSingleCaseMode {
	//JVM初始化，保证只有一个实例
	private static final HungryHanSingleCaseMode INSTANCE = new HungryHanSingleCaseMode();
	private HungryHanSingleCaseMode() {

	}

	public static HungryHanSingleCaseMode getInstance() {
		return INSTANCE;
	}

	public static void main(String[] args) {
		HungryHanSingleCaseMode instance1 = HungryHanSingleCaseMode.getInstance();
		HungryHanSingleCaseMode instance2 = HungryHanSingleCaseMode.getInstance();
		System.out.println(instance1 == instance2);
	}
}
