package com.mashibing;

import sun.misc.Contended;

/**
 * 单例模式之懒汉式
 * @author xcy
 * @date 2022/10/17 - 8:42
 */
public class DCL_Demo {
	public static void main(String[] args) {
		Instance instance = Instance.getInstance();
		System.out.println(instance);
	}
	static class Instance {
		//实现缓存行的效果
		@Contended
		private volatile static Instance instance = null;

		public static Instance getInstance() {
			//线程A
			//线程B

			//此时线程B开始执行，因为instance有了指向，所以判断instance不为空，线程B直接返回了
			if (instance == null) {
				synchronized (DCL_Demo.class) {
					if (instance == null) {
						//new的三步骤：
						//1.开辟内存空间
						//2.内部属性初始化
						//3.将地址给予引用到instance

						//因为指令重排序可能会打乱原有的123的顺序
						//此时线程A执行到这里，先执行了13，而没有执行2，导致虽然instance有指向，但是内部属性没有初始化
						instance = new Instance();
					}
				}
			}
			//此时线程B返回的instance内部属性没有初始化，会出现问题
			return instance;
		}
	}
}
