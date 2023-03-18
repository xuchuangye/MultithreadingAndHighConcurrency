package com.mashibing.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author xcy
 * @date 2021/8/11 - 15:45
 */
public class LockSupportTest {
	public static void main(String[] args) {
		Thread thread = new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread().getName() + " : " + i);
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (i == 5) {
					//park()方法让当前线层进入阻塞状态
					LockSupport.park();
				}
			}
		});
		/*try {
			TimeUnit.SECONDS.sleep(6);
			LockSupport.park(thread);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		thread.start();
		//可以让LockSupport.park()线程阻塞状态失效
		LockSupport.unpark(thread);

		/*try {
			TimeUnit.SECONDS.sleep(8);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("8 秒之后");

		//LockSupport可以指定具体的线程进行唤醒
		LockSupport.unpark(thread);*/
	}
}
