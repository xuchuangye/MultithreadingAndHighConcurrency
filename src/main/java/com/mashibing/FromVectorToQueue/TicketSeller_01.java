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
public class TicketSeller_01 {
	static List<String> tickets = new ArrayList<>();
	static {
		for (int i = 0; i < 10000; i++) {
			tickets.add("票的编号：" + i);
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				while (tickets.size() > 0) {
					System.out.println("销售了 " + tickets.remove(0));
				}
			}).start();
		}
	}
}
