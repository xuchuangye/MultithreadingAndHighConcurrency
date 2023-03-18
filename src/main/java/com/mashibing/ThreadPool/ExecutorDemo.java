package com.mashibing.ThreadPool;

import java.util.concurrent.Executor;

/**
 * @author xcy
 * @date 2021/8/15 - 8:20
 */

/**
 * Executor执行者
 */
public class ExecutorDemo implements Executor {
	public static void main(String[] args) {
		new ExecutorDemo().execute(() -> System.out.println("Executor"));
	}

	@Override
	public void execute(Runnable command) {
		command.run();
	}
}
