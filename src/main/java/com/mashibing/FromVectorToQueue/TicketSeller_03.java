package com.mashibing.FromVectorToQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xcy
 * @date 2021/8/14 - 8:23
 */

/**
 * N张火车票，每一张票都有编号
 * 同时有10个窗口对外销售
 */
public class TicketSeller_03 {
	static List<String> tickets = new ArrayList<>();

	static {
		for (int i = 0; i < 1000; i++) {
			tickets.add("票的编号：" + i);
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				while (true) {
					synchronized (tickets) {
						if (tickets.size() == 0) {
							break;
						}
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("销售了 " + tickets.remove(0));
					}
				}
			}).start();
		}
	}
}
