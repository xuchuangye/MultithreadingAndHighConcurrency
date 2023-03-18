package com.mashibing;

import static java.lang.Math.*;

/**
 * @author xcy
 * @date 2023/2/26 - 15:53
 */
public class CloneDemo1 {
	public static void main(String[] args) {
		Person person = new Person();
		person.age = 18;
		person.name = "张三";

		System.out.println(Math.sin(person.age));
		System.out.println(sin(person.age));

		try {
			Object obj = Class.forName("java.lang.Object").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

class Person {
	public String name;

	public int age;
}
