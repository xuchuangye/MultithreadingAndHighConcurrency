package com.mashibing.Multithreading;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author xcy
 * @date 2021/8/14 - 9:46
 */
public class ConcurrentQueueTest {
	public static void main(String[] args) {
		Queue<String> stringQueue = new ConcurrentLinkedQueue<>();

		for (int i = 0; i < 10; i++) {
			//offer添加，如果添加成功返回true，添加失败返回false
			stringQueue.offer("a" + i);//add()
		}

		System.out.println(stringQueue);
		System.out.println(stringQueue.size());

		//poll获取，并且remove
		System.out.println(stringQueue.poll());
		System.out.println(stringQueue.size());

		//peek获取，并且不会remove
		System.out.println(stringQueue.peek());
		System.out.println(stringQueue.size());
	}
}
