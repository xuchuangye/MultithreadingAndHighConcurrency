package com.mashibing;

/**
 * @author xcy
 * @date 2023/2/26 - 11:08
 */
public class OOPDemo2 {

	public static void main(String[] args) {
		//调用静态内部类的方法或者属性
		//调用静态内部类的静态方法或者属性
		String name = Outer.Inner.name;
		Outer.Inner.count(10);
		//调用静态内部类的普通方法或者属性
		int age = new Outer.Inner().age;
		new Outer.Inner().info();

		//创建普通内部类对象
		new Outer().new InnerClass();
		//创建静态内部类对象
		new Outer.Inner();
	}
}

class Outer {
	/**
	 * 静态内部类
	 */
	static class Inner {
		public static String name = "张三";

		public int age = 10;

		public void info() {
			System.out.println("info");
		}

		public static void count(int num) {
			System.out.println("计数：" + num);
		}
	}

	class InnerClass {

	}

	public void show() {
		//为什么需要final关键字修饰？因为方法和局部变量的生命周期周期需要同步

		//在外部类方法中局部变量会在方法执行结束后直接回收，而局部内部类还没有被回收
		//此时局部内部类使用外部类方法的局部变量就会找不到，所以外部类的方法的局部变量就需要使用final关键字修饰
		//局部变量被final关键字修饰之后，就会成为常量，外部类方法执行结束后也不会回收
		//此时局部内部类使用外部类方法的局部变量就不会报错
		final int num = 10;
		class FunctionInner {
			public void print() {
				System.out.println(num);
			}
		}

		FunctionInner functionInner = new FunctionInner();
		functionInner.print();
	}
}