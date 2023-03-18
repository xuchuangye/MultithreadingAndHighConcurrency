package com.mashibing.Multithreading;

/**
 * @author xcy
 * @date 2021/8/14 - 10:45
 */

import java.sql.Time;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 有界的
 */
public class ArrayBlockingQueueTest {
	//ArrayBlockingQueue必须要设置容量
	static BlockingQueue<String> stringBlockingQueue = new ArrayBlockingQueue<String>(10);
	static Random random = new Random();

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			try {
				stringBlockingQueue.put("a" + i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			//此时已经满了，所以会进入阻塞状态
			//stringBlockingQueue.put("aaa");

			//add添加失败就会报异常java.lang.IllegalStateException: Queue full
			//stringBlockingQueue.add("aaa");

			//offer添加失败返回false
			//System.out.println(stringBlockingQueue.offer("aaa"));//false

			//参数一，需要添加的元素，参数二：时间长度，参数三：时间单位
			stringBlockingQueue.offer("aaa",1,TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}


		System.out.println(stringBlockingQueue);

		/*new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(10);
				stringBlockingQueue.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

		System.out.println(stringBlockingQueue);*/
	}
}
