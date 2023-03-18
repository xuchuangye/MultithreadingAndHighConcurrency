package com.mashibing.threadend;

import com.mashibing.threadbasic.SleepHelper;

/**
 * @author xcy
 * @date 2021/8/19 - 10:23
 */

/**
 * stop结束线程
 * 为什么不建议使用stop结束线程？
 * stop强行将线程内所有的锁全部都释放，并且不作任何处理
 * 非常容易出现数据不一致的问题
 */
public class Stop {
	public static void main(String[] args) {
		Thread thread = new Thread(() -> {
			while (true) {
				System.out.println("go on");
				SleepHelper.sleepSeconds(1);
			}
		});

		thread.start();

		SleepHelper.sleepSeconds(5);

		thread.stop();
	}
}
