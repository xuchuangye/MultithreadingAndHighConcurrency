package com.mashibing.ThreadPool;

/**
 * @author xcy
 * @date 2021/8/15 - 9:30
 */

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFuture本身就可以当做任务Task，并且能够管理多个Future
 */
public class CompletableFutureDemo {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		long start, end;
		start = System.currentTimeMillis();

		CompletableFuture<Double> futureTM = CompletableFuture.supplyAsync(() -> priceOfTM());
		CompletableFuture<Double> futureTB = CompletableFuture.supplyAsync(() -> priceOfTB());
		CompletableFuture<Double> futureJD = CompletableFuture.supplyAsync(() -> priceOfJD());

		CompletableFuture.supplyAsync(CompletableFutureDemo::priceOfTM)
				.thenApply(String::valueOf)
				.thenApply(str -> "price" + str)
				.thenAccept(System.out::println).get();
		//allOf方法表示futureTM, futureTB, futureJD所有的方法必须都执行完之后，才能往下执行
		CompletableFuture.allOf(futureTM, futureTB, futureJD).join();
		//anyOf方法表示futureTM, futureTB, futureJD所有的方法当中任意一个执行完成之后，就能往下执行
		CompletableFuture.anyOf(futureTM, futureTB, futureJD).join();

		end = System.currentTimeMillis();
		System.out.println("use completable future !" + (end - start));
	}

	private static double priceOfJD() {
		delay();
		return 1.00;
	}

	private static double priceOfTB() {
		delay();
		return 2.00;
	}

	private static double priceOfTM() {
		delay();
		return 3.00;
	}

	private static void delay() {
		int time = new Random().nextInt(500);
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("After %s sleep!\n",time);
	}
}
