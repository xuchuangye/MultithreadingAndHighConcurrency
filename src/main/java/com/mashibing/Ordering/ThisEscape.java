package com.mashibing.Ordering;

import com.mashibing.threadbasic.SleepHelper;

import java.io.IOException;

/**
 * @author xcy
 * @date 2021/8/20 - 10:23
 */

/**
 * this对象溢出
 */
public class ThisEscape {
	private int num = 8;
	private Thread thread = null;

	//构造方法中可以创建线程，但是线程尽量不要在构造方法中启动
	public ThisEscape() {
		thread = new Thread(() -> System.out.println(this.num));
	}
	//线程可以在普通方法中启动
	public void start() {
		thread.start();
	}

	public static void main(String[] args) throws IOException {
		new ThisEscape();
		System.in.read();
	}
}
