package com.mashibing.VarHandle;

import java.lang.invoke.MethodHandles;
//import java.lang.invoke.VarHandle;

/**
 * @author xcy
 * @date 2021/8/12 - 21:37
 */
public class VarHandleTest {
	int x = 8;
	//private static VarHandle varHandle;

	/*static {
		try {
			//返回lookup对象，findVarHandle()指向指定的对象或者变量
			varHandle = MethodHandles.lookup().findVarHandle(VarHandleTest.class, "x", int.class);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}*/

	public static void main(String[] args) {
		VarHandleTest varHandleTest = new VarHandleTest();

		//通过VarHandle引用获取指定变量的值
		//System.out.println(varHandle.get(varHandleTest));
		//通过VarHandle引用设置指定变量的值
		//varHandle.set(varHandleTest, 10);
		System.out.println(varHandleTest.x);

		//进行原子性的操作，不需要加锁
		//varHandle.compareAndSet(varHandleTest, 11, 10);
		System.out.println(varHandleTest.x);

		//进行原子性的操作，不需要加锁
		//varHandle.getAndAdd(varHandleTest, 100);
		System.out.println(varHandleTest.x);
	}
}
