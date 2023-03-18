package com.mashibing.synchronized_;

/**
 * @author xcy
 * @date 2021/8/10 - 10:13
 */

import java.util.concurrent.TimeUnit;

/**
 * synchronized的细化和粗化
 */
public class FineCoarseLock {
	int count = 0;

	public synchronized void method1() {
		//业务逻辑
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		count++;

		//业务逻辑
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public void method2() {
		//业务逻辑
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized (this) {
			count++;
		}
		//业务逻辑
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
