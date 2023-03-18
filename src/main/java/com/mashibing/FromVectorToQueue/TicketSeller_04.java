package com.mashibing.FromVectorToQueue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author xcy
 * @date 2021/8/14 - 8:23
 */

/**
 * N张火车票，每一张票都有编号
 * 同时有10个窗口对外销售
 * 多线程尽量 考虑Queue
 */
public class TicketSeller_04 {
	static Queue<String> tickets = new ConcurrentLinkedQueue<>();

	static {
		for (int i = 0; i < 10000; i++) {
			tickets.add("票的编号：" + i);
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				while (true) {
					//从tickets队列中取值，如果有值返回该值，如果没值返回null
					String poll = tickets.poll();
					//当取不到值时，表示队列已经空了，直接返回即可
					if (poll == null) {
						break;
					} else {
						System.out.println("销售了 " + poll);
					}
				}
			}).start();
		}
	}
}
