package com.mashibing.threadbasic;

import java.util.concurrent.TimeUnit;

/**
 * @author xcy
 * @date 2021/8/18 - 17:52
 */
public class SleepHelper {
	public static void sleepSeconds(int i) {
		try {
			TimeUnit.SECONDS.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
