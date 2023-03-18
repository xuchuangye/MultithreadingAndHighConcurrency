package com.mashibing.InterviewQuestions3;

/**
 * @author xcy
 * @date 2021/8/12 - 10:01
 */

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 写一个固定容量的同步容器，拥有put方法和get方法，以及getCount方法
 * 能够支持两个生产者线程以及10个消费者线程的阻塞调用
 *
 * 使用wait、notify/notifyAll来实现
 */
public class MyContainerWithWaitNotify<T> {
	//为什么使用LinkedList，因为LinkedList添加删除的效率要比ArrayList高
	final private LinkedList<T> lists = new LinkedList<T>();
	final private int MAX = 10;//最多10个消费者线程
	int count = 0;

	//生产者
	public synchronized void put(T t) {
		//如果lists集合的长度已经满了，则表示生产者要停止生产商品了
		while (lists.size() == MAX) {
			try {
				//该线程就等待消费者线程去消费
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		lists.add(t);
		++count;
		//通知消费者进行消费
		this.notifyAll();
	}

	public synchronized T get() {
		//如果lists集合为空，则表示消费者已经无法在继续消费商品了
		while (lists.size() == 0) {
			try {
				//该线程就等待生产者线程生产商品
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		T t  = lists.removeFirst();
		count--;
		//通知生产者线程进行生产
		this.notifyAll();
		return t;
	}

	public static void main(String[] args) {
		MyContainerWithWaitNotify<String> myContainer = new MyContainerWithWaitNotify<String>();
		//启动消费者线程consumer
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				for (int j = 0; j < 5; j++) {
					System.out.println(myContainer.get());
				}
			},"consumer" + i).start();
		}

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//启动生产者线程producer

		for (int i = 0; i < 2; i++) {
			new Thread(() -> {
				for (int j = 0; j < 25; j++) {
					myContainer.put(Thread.currentThread().getName() + " : " + j);
				}
			},"producer" + i).start();
		}

	}
}
