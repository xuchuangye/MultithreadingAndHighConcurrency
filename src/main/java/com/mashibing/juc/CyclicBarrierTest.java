package com.mashibing.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author xcy
 * @date 2021/8/11 - 8:05
 */
public class CyclicBarrierTest {
	public static void main(String[] args) {
		//满20个之后继续执行
		//CyclicBarrier cyclicBarrier = new CyclicBarrier(20);
		//参数一：满20个人才继续往下执行，然后满员之后执行的run方法
		/*CyclicBarrier cyclicBarrier = new CyclicBarrier(20, new Runnable() {
			@Override
			public void run() {
				System.out.println("预备！开始！");
			}
		});*/
		//
		CyclicBarrier cyclicBarrier = new CyclicBarrier(20, () -> System.out.println("满人，上车"));


		for (int i = 0; i < 100; i++) {
			new Thread(() -> {
				try {
					cyclicBarrier.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}
			}).start();
		}
	}
}
