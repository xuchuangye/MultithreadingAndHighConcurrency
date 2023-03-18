package com.mashibing.test;

import java.util.concurrent.TimeUnit;

/**
 * @author xcy
 * @date 2021/8/9 - 17:30
 */

/**
 * 测试volatile
 */
public class TestVolatile {
	//volatile透明的
	/*volatile*/ boolean running = true;
	public synchronized void method() {
		System.out.println("method start");
		//进入死循环
		while(running) {

		}
		System.out.println("method end");
	}

	public static void main(String[] args) {
		TestVolatile testVolatile = new TestVolatile();

		//new Thread(testVolatile::method).start();
		new Thread(() -> {
			testVolatile.method();
		}).start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		testVolatile.running = false;
	}
}
