package com.mashibing.Multithreading;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author xcy
 * @date 2021/8/14 - 11:24
 */

/**
 * PriorityQueue类是Queue的实现类
 * 底层是二叉树，使用堆排序进行排序，最小的值优先级最大
 */
public class PriorityQueueTest {
	public static void main(String[] args) {
		Queue<String> stringQueue = new PriorityQueue<>();

		stringQueue.offer("a");
		stringQueue.offer("g");
		stringQueue.offer("z");
		stringQueue.offer("h");
		stringQueue.offer("k");

		/*for (int i = 0; i < stringQueue.size(); i++) {
			System.out.println(stringQueue.poll());
		}*/
		//stringQueue.forEach(System.out::println);

		for (int i = 0; i < 5; i++) {
			System.out.println(stringQueue.poll());
		}
	}
}
