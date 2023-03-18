package com.mashibing.InterviewQuestions3;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xcy
 * @date 2021/8/12 - 11:16
 */
public class MyContainerWithReentrantLock<T> {
	//为什么使用LinkedList，因为LinkedList添加删除的效率要比ArrayList高
	private final LinkedList<T> lists = new LinkedList<T>();
	private final int MAX = 10;//最多10个消费者线程
	int count = 0;
	private final Lock lock = new ReentrantLock();
	//Condition可以精确的指定哪些线程被唤醒，Condition的本质就是等待队列个数
	private final Condition producer = lock.newCondition();
	private final Condition costumer = lock.newCondition();

	/**
	 * 生产者线程
	 */
	public synchronized void put(T t) {
		try {
			lock.lock();
			//如果lists集合的长度已经满了，则表示生产者要停止生产商品了
			while (lists.size() == MAX) {
				//该线程就等待消费者线程去消费
				producer.await();
			}
			lists.add(t);
			++count;
			//通知唤醒消费者线程进行消费
			costumer.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 消费者线程
	 * @return 返回消费者消费的商品
	 */
	public synchronized T get() {
		T t = null;
		try {
			lock.lock();
			t = lists.removeFirst();
			//如果lists集合为空，则表示消费者已经无法在继续消费商品了
			while (lists.size() == 0) {
				//该线程就等待生产者线程生产商品
				costumer.await();
			}
			count--;
			//通知生产者线程进行生产
			producer.notifyAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return t;
	}

	public static void main(String[] args) {
		MyContainerWithReentrantLock<String> myContainer = new MyContainerWithReentrantLock<String>();
		//启动消费者线程consumer
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				for (int j = 0; j < 5; j++) {
					System.out.println(myContainer.get());
				}
			}, "consumer" + i).start();
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
			}, "producer" + i).start();
		}

	}
}
