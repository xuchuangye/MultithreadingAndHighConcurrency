package com.mashibing.InterviewQuestions4;

import com.mashibing.test.bean.ChopStick;
import com.mashibing.threadbasic.SleepHelper;

/**
 * @author xcy
 * @date 2021/8/29 - 20:56
 */

/**
 * 哲学家吃饭问题
 */
public class DeadLock {
	public static void main(String[] args) {
		ChopStick cs0 = new ChopStick();
		ChopStick cs1 = new ChopStick();
		ChopStick cs2 = new ChopStick();
		ChopStick cs3 = new ChopStick();
		ChopStick cs4 = new ChopStick();

		Philosopher p0 = new Philosopher("p0", 0, cs0, cs1);
		Philosopher p1 = new Philosopher("p1", 1, cs1, cs2);
		Philosopher p2 = new Philosopher("p2", 2, cs2, cs3);
		Philosopher p3 = new Philosopher("p3", 3, cs3, cs4);
		Philosopher p4 = new Philosopher("p4", 4, cs4, cs0);

		p0.start();
		p1.start();
		p2.start();
		p3.start();
		p4.start();
	}
}

class Philosopher extends Thread {
	ChopStick left, right;
	private String name;
	private int index;

	public Philosopher() {
	}

	public Philosopher(String name, Integer index, ChopStick left, ChopStick right) {
		this.setName(name);
		this.index = index;
		this.left = left;
		this.right = right;
	}


	@Override
	public void run() {
		//混进一个左撇子
		if (index == 0) {
			synchronized (right) {
				SleepHelper.sleepSeconds(1);
				synchronized (left) {
					SleepHelper.sleepSeconds(1);
					System.out.println(index + "吃完了！");
				}
			}
		}
		if (index != 0){
			synchronized (left) {
				SleepHelper.sleepSeconds(1);
				synchronized (right) {
					SleepHelper.sleepSeconds(1);
					System.out.println(index + "吃完了！");
				}
			}
		}
	}
}