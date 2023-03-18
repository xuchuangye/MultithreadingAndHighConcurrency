package com.mashibing.ThreadPool.ThreadPoolExecutor;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author xcy
 * @date 2021/8/15 - 16:24
 */

/**
 * ScheduledThreadPool
 */
public class ScheduledPoolDemo {
	public static void main(String[] args) {
		final int cpuCoreNum = 4;
		ScheduledExecutorService service = Executors.newScheduledThreadPool(cpuCoreNum);
		service.scheduleAtFixedRate(() -> {
			//Runnable
			try {
				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName());
		},
		//初始延迟时间
		0,
		//间隔时间  //时间单位毫秒
		500,TimeUnit.MILLISECONDS);
	}
}
