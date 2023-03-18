package com.mashibing.juc;

/**
 * @author xcy
 * @date 2021/8/11 - 11:05
 */

import java.util.concurrent.Exchanger;

/**
 * 交换器
 */
public class ExchangerTest2 {
	static class Person {
		String name;
		int age;

		public Person(String name, int age) {
			this.name = name;
			this.age = age;
		}

		@Override
		public String toString() {
			return "Person{" +
					"name='" + name + '\'' +
					", age=" + age +
					'}';
		}
	}
	static Exchanger<Person> exchanger = new Exchanger<Person>();

	public static void main(String[] args) {
		new Thread(() -> {
			Person person = new Person("赵丽颖",33);
			try {
				//当第一个线程执行exchange方法时，必须要有第二个线程也执行exchange方法，否则第一个线程会一直处于阻塞状态
				person = exchanger.exchange(person);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " : " + person);
		}, "thread1").start();

		new Thread(() -> {
			Person person = new Person("徐创业",27);
			try {
				person = exchanger.exchange(person);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " : " + person);
		}, "thread2").start();
	}
}
