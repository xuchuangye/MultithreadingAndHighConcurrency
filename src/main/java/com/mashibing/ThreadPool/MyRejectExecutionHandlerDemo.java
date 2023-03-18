package com.mashibing.ThreadPool;

import java.util.concurrent.*;

/**
 * @author xcy
 * @date 2021/8/16 - 8:28
 */
public class MyRejectExecutionHandlerDemo {
	public static void main(String[] args) {
		ExecutorService service = new ThreadPoolExecutor(4,4,0L,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue<>(6), Executors.defaultThreadFactory(),
				new MyRejectExecutionHandler());
	}

	static class MyRejectExecutionHandler implements RejectedExecutionHandler {

		@Override
		public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
			//log()
			//
			if (executor.getQueue().size() < 10000) {
				//try put again()
			}
		}
	}
}
