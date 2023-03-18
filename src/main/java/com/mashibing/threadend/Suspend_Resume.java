package com.mashibing.threadend;

import com.mashibing.threadbasic.SleepHelper;

/**
 * @author xcy
 * @date 2021/8/19 - 10:23
 */

/**
 * suspend/resume结束线程
 * 为什么不建议使用suspend、resume结束线程？
 * suspend/resume容易出现数据不一致的问题，很容易出现死锁的问题
 */
public class Suspend_Resume {
	public static void main(String[] args) {
		Thread thread = new Thread(() -> {
			while (true) {
				System.out.println("go on");
				SleepHelper.sleepSeconds(1);
			}
		});

		thread.start();

		SleepHelper.sleepSeconds(5);
		//suspend暂停
		thread.suspend();

		SleepHelper.sleepSeconds(5);
		//resume恢复
		thread.resume();
	}
}
