package com.mashibing.juc;

import java.util.concurrent.Semaphore;

/**
 * @author xcy
 * @date 2021/8/11 - 10:42
 */

/**
 * 信号灯
 */
public class SemaphoreTest {
	public static void main(String[] args) {
		//Semaphore semaphore = new Semaphore(2);
		//fair：设置公平锁与非公平锁的参数，true设置公平锁，false设置非公平锁
		Semaphore semaphore = new Semaphore(2,true);

		new Thread(() -> {
			try {
				semaphore.acquire();
				System.out.println("Thread1 start");
				Thread.sleep(200);
				System.out.println("Thread1 end");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaphore.release();
			}

		}).start();

		new Thread(() -> {
			try {
				semaphore.acquire();
				System.out.println("Thread2 start");
				Thread.sleep(200);
				System.out.println("Thread2 end");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				semaphore.release();
			}

		}).start();

		new Thread(() -> {
			try {
				semaphore.acquire();
				System.out.println("Thread3 start");
				Thread.sleep(200);
				System.out.println("Thread3 end");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				semaphore.release();
			}

		}).start();
	}
}
