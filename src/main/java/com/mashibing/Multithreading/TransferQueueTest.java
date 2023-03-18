package com.mashibing.Multithreading;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @author xcy
 * @date 2021/8/14 - 11:46
 */
public class TransferQueueTest {
	public static void main(String[] args) throws Exception {
		LinkedTransferQueue<String> linkedTransferQueue = new LinkedTransferQueue<>();

		new Thread(() -> {
			try {
				System.out.println(linkedTransferQueue.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

		linkedTransferQueue.transfer("aaa");

		new Thread(() -> {
			try {
				System.out.println(linkedTransferQueue.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}
}
