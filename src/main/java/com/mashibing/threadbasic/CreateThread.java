package com.mashibing.threadbasic;

/**
 * @author xcy
 * @date 2021/8/18 - 16:26
 */

import java.util.concurrent.*;

/**
 * 创建线程的方式
 * 继承Thread类
 * 实现Runnable接口
 * 实现Callable接口
 *
 * 创建线程池的方式
 * 创建FutureTask类，将来会产生返回值的任务，既能当做任务来运行，也能有自己的返回值
 * 创建线程池Executors.newCachedThreadPool()
 */
public class CreateThread {
	static class MyThread extends Thread {
		@Override
		public void run() {
			System.out.println("Mode one : extends Thread class");
		}
	}

	static class MyRun implements Runnable {

		@Override
		public void run() {
			System.out.println("Mode two : implements Runnable interface");
		}
	}

	static class MyCall implements Callable<String> {

		@Override
		public String call() throws Exception {
			return "Mode three : implements Callable interface";
		}
	}

	public static void main(String[] args) {
		//创建线程的方式
		new MyThread().start();

		new Thread(new MyRun()).start();

		new Thread(() -> {
			System.out.println("匿名实现类的方式");
		}).start();

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//创建线程池的方式
		FutureTask<String> futureTask = new FutureTask<>(new MyCall());
		Thread thread = new Thread(futureTask);
		thread.start();

		ExecutorService service = Executors.newCachedThreadPool();
		//submit方法具有返回值
		Future<String> submit = service.submit(new MyCall());
		try {
			//get方法是一个带阻塞的方法，直到获取到将来的数据Future之前，一直处于阻塞状态
			System.out.println(submit.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		//execute方法没有返回值
		service.execute(new MyRun());
		//FutureTask将来会产生返回值的任务，既能当做任务来运行，也能有自己的返回值
		FutureTask<String> task = new FutureTask<>(new MyCall());

		service.submit(task);
		service.shutdown();
	}
}
