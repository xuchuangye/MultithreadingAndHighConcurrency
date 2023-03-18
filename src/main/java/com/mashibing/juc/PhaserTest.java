package com.mashibing.juc;

/**
 * @author xcy
 * @date 2021/8/11 - 9:06
 */

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Phaser类不仅可以控制栅栏的个数，还可以控制栅栏上等待的线程的数量
 */
public class PhaserTest {
	static Random random = new Random();
	static MarriagePhaser phaser = new MarriagePhaser();

	static void milliSleep(int milli) {
		try {
			TimeUnit.MILLISECONDS.sleep(milli);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	static class Person implements Runnable {
		String name;

		public Person(String name) {
			this.name = name;
		}

		//进场
		public void arrive() {
			milliSleep(random.nextInt(1000));
			System.out.println(name + "到达现场");
			//等待进入下一个阶段
			phaser.arriveAndAwaitAdvance();
		}

		//吃饭
		public void eat() {
			milliSleep(random.nextInt(1000));
			System.out.println(name + "吃完！");
			phaser.arriveAndAwaitAdvance();
		}

		//leave
		public void leave() {
			milliSleep(random.nextInt(1000));
			System.out.println(name + "离开！");
			phaser.arriveAndAwaitAdvance();
		}

		public void hug() {
			if ("新郎".equalsIgnoreCase(name) || "新娘".equalsIgnoreCase(name)) {
				milliSleep(random.nextInt(1000));
				System.out.println(name + "到达现场");
				phaser.arriveAndAwaitAdvance();
			} else {
				//取消注册，不进入下一阶段
				phaser.arriveAndDeregister();
			}
		}

		@Override
		public void run() {
			arrive();
			eat();
			leave();
			hug();
		}
	}

	static class MarriagePhaser extends Phaser {
		/**
		 * 当栅栏被推倒的时候会自动调用
		 *
		 * @param phase             当前阶段
		 * @param registeredParties 目前有多少人参加，根据注册的人数来决定
		 * @return 所有的阶段全部结束之后再返回
		 */
		@Override
		protected boolean onAdvance(int phase, int registeredParties) {
			switch (phase) {
				case 0:
					System.out.println("所有的人都到齐了！" + registeredParties);
					System.out.println();
					return false;
				case 1:
					System.out.println("所有的人都吃完饭了！" + registeredParties);
					System.out.println();
					return false;
				case 2:
					System.out.println("所有的人都离开了！" + registeredParties);
					System.out.println();
					return false;
				case 3:
					System.out.println("婚礼结束！新郎新娘抱抱！" + registeredParties);
					System.out.println();
					return true;
				default:
					return true;
			}
		}
	}

	public static void main(String[] args) {
		//注册人数
		phaser.bulkRegister(7);

		//客人
		for (int i = 1; i <= 5; i++) {
			final int nameIndex = 1;
			new Thread(new Person("p" + i)).start();
		}

		new Thread(new Person("新郎")).start();
		new Thread(new Person("新娘")).start();
	}
}
