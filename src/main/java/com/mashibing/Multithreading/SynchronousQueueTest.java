package com.mashibing.Multithreading;

/**
 * @author xcy
 * @date 2021/8/14 - 11:37
 */

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * SynchronousQueue类是BlockingQueue的实现类
 * 并且该容器的容量为0
 */
public class SynchronousQueueTest {
	public static void main(String[] args) {
		BlockingQueue<String> stringBlockingQueue = new SynchronousQueue<>();

		new Thread(() -> {
			try {
				//取出数据，直到获取到数据之前一直处于阻塞状态
				System.out.println(stringBlockingQueue.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

		try {
			stringBlockingQueue.put("aaa");
			//添加数据，如果添加失败则一直处于阻塞状态
			stringBlockingQueue.put("bbb");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(stringBlockingQueue.size());
	}
}
